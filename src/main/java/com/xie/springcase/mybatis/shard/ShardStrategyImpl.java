package com.xie.springcase.mybatis.shard;

import com.xie.springcase.mybatis.domain.Employee;

/**
 * Created by Administrator on 2016/4/21.
 */
public class ShardStrategyImpl implements ShardStrategy {
    /**
     * 得到实际表名
     * @parambaseTableName逻辑表名,一般是没有前缀或者是后缀的表名
     * @param params        mybatis执行某个statement时使用的参数
     * @param mapperId      mybatis配置的statement id
     * @return
     */
    @Override
    public String getTargetTableName(String baseTableName,Object params, String mapperId) {
        Integer k = 0;
        if (params !=null) {
            Employee shardTestBean = (Employee) params;
            Integer temp = Integer.valueOf(shardTestBean.getId());
            k= temp % 2;
        }
        return baseTableName +"_" + k;
    }
}
