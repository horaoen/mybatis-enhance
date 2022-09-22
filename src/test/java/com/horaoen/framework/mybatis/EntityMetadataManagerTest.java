package com.horaoen.framework.mybatis;

import com.hoaoen.framework.mybatis.annotation.Id;
import com.hoaoen.framework.mybatis.annotation.Table;
import com.hoaoen.framework.mybatis.core.EntityMetadata;
import com.hoaoen.framework.mybatis.core.EntityMetadataManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * @author horaoen
 */
public class EntityMetadataManagerTest {
    @Test
    public void test() {
        EntityMetadata metadata = EntityMetadataManager.getMetadata(TestEntity.class);
        System.out.println(metadata);
    }
    
    @Table("test")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TestEntity {
        @Id
        private String id;
        
        private String name;
        
        private String password;
    }
}
