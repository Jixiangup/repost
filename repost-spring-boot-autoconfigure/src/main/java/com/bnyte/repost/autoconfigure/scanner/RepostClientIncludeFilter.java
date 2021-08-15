package com.bnyte.repost.autoconfigure.scanner;

import com.bnyte.core.annotation.bind.RepostClient;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class RepostClientIncludeFilter implements TypeFilter {

    public RepostClientIncludeFilter() {
    }

    /**
     * 只要将当前获取到的资源转换为字节码对象时不为空就让他注册
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        /*String className = metadataReader.getClassMetadata().getClassName();
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException ignored) { }
        return clazz != null;*/
        return true;
    }


}
