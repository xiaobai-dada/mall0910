package com.hc.mall.manageservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.hc.mall.bean.*;
import com.hc.mall.config.RedisUitl;
import com.hc.mall.manageservice.costant.ManageConst;
import com.hc.mall.manageservice.mapper.*;
import com.hc.mall.service.ManageService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ManageServiceImpl implements ManageService {


    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    BaseCatalog3Mapper baseCatalog3Mapper;

    @Autowired
    SpuInfoMapper spuInfoMapper;

    @Autowired
    BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private SpuImageMapper spuImageMapper;

    @Autowired
    private  SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    RedisUitl redisUitl;





    @Override
    public List<BaseCatalog1> getCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        // select * from baseCatalog2 where catalog1Id = ?
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return baseCatalog2Mapper.select(baseCatalog2);
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        return baseCatalog3Mapper.select(baseCatalog3);
    }

    // 根据三级分类id查询平台属性和平台属性值
    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
//        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
//        baseAttrInfo.setCatalog3Id(catalog3Id);
//        return baseAttrInfoMapper.select(baseAttrInfo);

        return baseAttrInfoMapper.getBaseAttrInfoListByCatalog3Id(catalog3Id);
    }

//    @Override
//    public List<BaseAttrValue> getAttrValueList(String attrId) {
//        BaseAttrValue baseAttrValue = new BaseAttrValue();
//        baseAttrValue.setAttrId(attrId);
//        return baseAttrValueMapper.select(baseAttrValue);
//    }

    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {

        // baseAttrInfo.id = baseAttrValue.getAttrId()
        BaseAttrInfo baseAttrInfo= baseAttrInfoMapper.selectByPrimaryKey(attrId);

        // 需要将该平台属性的值（集合）放入该平台对象的 attrValueList
        // select * from baseAttrValue where attrId= ?
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId); //baseAttrInfo.getId()
        List<BaseAttrValue> baseAttrValueList =  baseAttrValueMapper.select(baseAttrValue);
        baseAttrInfo.setAttrValueList(baseAttrValueList);

        return baseAttrInfo;
    }

    @Transactional   // 声明式事务
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

        // 如果有主键就就行更新，没有主键就添加
        //if(baseAttrInfo.getId() !=null || baseAttrInfo.getId().length() > 0){
        if(!StringUtils.isEmpty(baseAttrInfo.getId())){
            //更新
            baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else{
            //添加
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }


        // 先清空数据，在插入到数据库中即可！
        // 清空数据不是把baseAttrValue表中的数据全部清空，所有要添加条件
        // 条件是 ： 把【当前平台属性】所对应的值全部删除
        // delete from baseAttrValue where attrId = baseAttrInfo.getId();
        BaseAttrValue baseAttrValueDel = new BaseAttrValue();
        baseAttrValueDel.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValueDel);


        // BaseAttrValue
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();

        if(attrValueList!= null && attrValueList.size()>0){

            for (BaseAttrValue baseAttrValue : attrValueList) {
                // private String id;
                //private String valueName; // 前台传过来的
                //private String attrId;   // attrId = baseAttrInfo.getId();
                // baseAttrInfo.getId()的前提条件是  baseAttrInfo对象中的主键必须能过获取到自增的值，所以我们需要去实体类做主键回填

                // baseAttrValue.setId("666"); // 测试事务控制的

                // baseAttrValue.setId(null); // 防止主键是空的字符串
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }

        }


    }

    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo) {
        return spuInfoMapper.select(spuInfo);
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {

        // 保存数据
        //        spuInfo
        //        spuImage
        //        spuSaleAttr
        //        spuSaleAttrValue

        // 保存spu的名称、描述
        spuInfoMapper.insertSelective(spuInfo);

        // 保存sup的图片
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (spuImageList!=null && spuImageList.size()>0){
            for (SpuImage spuImage : spuImageList) {
                // 设置spuId
                spuImage.setSpuId(spuInfo.getId());
                spuImageMapper.insertSelective(spuImage);
            }
        }

        // 保存sup的销售属性
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (spuSaleAttrList!=null && spuSaleAttrList.size()>0){
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setSpuId(spuInfo.getId());
                spuSaleAttrMapper.insertSelective(spuSaleAttr);

                // spuSaleAttrValue
                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                if (spuSaleAttrValueList!=null && spuSaleAttrValueList.size()>0){
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        spuSaleAttrValue.setSpuId(spuInfo.getId());
                        spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
                    }
                }
            }
        }

    }

    @Override
    public List<SpuImage> getSpuImageList(SpuImage spuImage) {

        // select * from spuImage where spuId = spuImage.getSpuId();
        return  spuImageMapper.select(spuImage);
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId) {
        // 调用mapper
        List<SpuSaleAttr> spuSaleAttrs= spuSaleAttrMapper.selectSpuSaleAttrList(spuId);
        return spuSaleAttrs;
    }

    @Override
    @Transactional
    public void saveSkuInfo(SkuInfo skuInfo) {

        //skuInfo:
        skuInfoMapper.insertSelective(skuInfo);

        //skuImage:
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if (skuImageList!=null && skuImageList.size()>0){
            for (SkuImage skuImage : skuImageList) {
                // skuImage.skuId = skuInfo.getId();
                skuImage.setSkuId(skuInfo.getId());
                skuImageMapper.insertSelective(skuImage);
            }
        }
//        skuAttrValue:
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();

        //     集合长度：.size(); 字符串：length()  数组 length; 文件长度 length();


        if (skuAttrValueList!=null && skuAttrValueList.size()>0){
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());
                skuAttrValueMapper.insertSelective(skuAttrValue);
            }
        }

//        skuSaleAttrValue:
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (skuSaleAttrValueList!=null && skuSaleAttrValueList.size()>0){
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
            }
        }

    }

    @Override
    public SkuInfo getSkuInfo(String skuId) {
        return getSkuInfoRedisLock(skuId);
    }



    // 4.redisson
    private SkuInfo getSkuInfoRedisson(String skuId) {
        // 放入业务逻辑代码
        SkuInfo skuInfo =null;
        Jedis jedis = null;
        RLock lock = null;

        try {
            Config config = new Config();
            config.useSingleServer().setAddress("redis://192.168.1.136");

            RedissonClient redissonClient = Redisson.create(config);

            // 使用redisson 调用 getLock
            lock = redissonClient.getLock("yourLock");

            // 加锁
            lock.lock(10, TimeUnit.SECONDS);

            jedis = redisUitl.getJedis();

            // 定义key     sku：skuId:info
            String skuKey = ManageConst.SKUKEY_PREFIX+skuId+ManageConst.SKUKEY_SUFFIX;

            // 判断缓存中是否有数据，如果有，从缓存中获取，没有从db获取并将数据放入缓存！
            // 判断redis 中是否有key
            if (jedis.exists(skuKey)){

                // 取得key 中的value
                String skuJson = jedis.get(skuKey);

                // 将字符串转换为对象
                skuInfo = JSON.parseObject(skuJson,SkuInfo.class);
                // jedis。close
                return skuInfo;
            }else {
                skuInfo = getSkuInfoDB(skuId);
                return skuInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null){
                jedis.close();
            }
            if (lock != null){
                lock.unlock();
            }
        }
        return getSkuInfoDB(skuId);
    }

    // 3. 解决缓存击穿问题使用redis加锁
    private SkuInfo getSkuInfoRedisLock(String skuId) {

        Jedis jedis = null;
        SkuInfo skuInfo = null;

        try {
            jedis = redisUitl.getJedis();
            // 拼接redis商品的key  sku:35:indo
            String skuKey = ManageConst.SKUKEY_PREFIX + skuId + ManageConst.SKUKEY_SUFFIX;
            // 获取
            String skuJson = jedis.get(skuKey);

            if (skuJson == null || skuJson.length() == 0){
                // 开始加锁
                System.out.println("缓冲失效了");

                //  sku:35:lock
                // 执行set方法上锁
                String skuLockKey = ManageConst.SKUKEY_PREFIX + skuId + ManageConst.SKULOCK_SUFFIX;
                String lockKey = jedis.set(skuLockKey, "123", "NX", "PX", ManageConst.SKULOCK_EXPIRE_PX);

                if ("OK".equals(lockKey)){
                    // 加锁成功
                    System.out.println("加锁成功了");
                    skuInfo = getSkuInfoDB(skuId);
                    String skuRedisStr = JSON.toJSONString(skuInfo);
                    jedis.setex(skuKey,ManageConst.SKUKEY_TIMEOUT,skuRedisStr);

                    // 删除锁
                    jedis.del(skuLockKey);
                    return skuInfo;
                }else {
                    // 等待的睡眠时间
                    Thread.sleep(1000);
                    // 从新调用的
                    getSkuInfo(skuId);
                }
            }else {
                skuInfo = JSON.parseObject(skuJson,SkuInfo.class);
                return skuInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 如何解决空指针异常
            if (jedis != null){
                jedis.close();
            }
        }
        return getSkuInfoDB(skuId);
    }

    // 从数据库中获取商品详情
    private SkuInfo getSkuInfoDB(String skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuId);
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuId);
        List<SkuImage> images = skuImageMapper.select(skuImage);
        skuInfo.setSkuImageList(images);
        return skuInfo;
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(SkuInfo skuInfo) {

        return  spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(skuInfo.getId(),skuInfo.getSpuId());
    }

    @Override
    public List<SkuSaleAttrValue> getSkuSaleAttrValueListBySpu(String spuId) {
        // 根据spuid 查询数据
        return skuSaleAttrValueMapper.selectSkuSaleAttrValueListBySpu(spuId);
    }


}
