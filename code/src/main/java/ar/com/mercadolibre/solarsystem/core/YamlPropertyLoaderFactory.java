package ar.com.mercadolibre.solarsystem.core;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * Es un clase que permite la carga de archivos con formato YAML
 *
 * @see {@linktourl https://stackoverflow.com/a/45882447/4527110}
 */
public class YamlPropertyLoaderFactory implements PropertySourceFactory {

    public YamlPropertyLoaderFactory() {
    }

    public PropertySource<?> createPropertySource(@Nullable String name, EncodedResource encodedResource) throws IOException {
        Properties propertiesFromYaml = this.loadYamlIntoProperties(encodedResource);
        String sourceName = Optional.ofNullable(name).isPresent() ? name : encodedResource.getResource().getFilename();
        return new PropertiesPropertySource(sourceName, propertiesFromYaml);
    }

    private Properties loadYamlIntoProperties(EncodedResource resource) throws FileNotFoundException {
        try {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(new Resource[]{resource.getResource()});
            factory.afterPropertiesSet();
            return factory.getObject();
        } catch (IllegalStateException var4) {
            Throwable cause = var4.getCause();
            if (cause instanceof FileNotFoundException) {
                throw (FileNotFoundException) cause;
            } else {
                throw var4;
            }
        }
    }
}