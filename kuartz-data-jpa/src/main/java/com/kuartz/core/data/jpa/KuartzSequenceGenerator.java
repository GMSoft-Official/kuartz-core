package com.kuartz.core.data.jpa;

import com.kuartz.core.common.exception.ExceptionMessage;
import com.kuartz.core.common.exception.KzRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.relational.QualifiedName;
import org.hibernate.boot.model.relational.QualifiedNameParser;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.Table;
import java.util.Properties;

@Slf4j
public class KuartzSequenceGenerator extends SequenceStyleGenerator {


    private String numberFormat;

    @Override
    protected QualifiedName determineSequenceName(Properties params, Dialect dialect, JdbcEnvironment jdbcEnv,
                                                  ServiceRegistry serviceRegistry) {
        try {
            String entityName = params.getProperty(ENTITY_NAME);
            Class<?> entityClass = Class.forName(entityName);
            KuartzSequence sequence = entityClass.getAnnotation(KuartzSequence.class);
            Table tableAnnotation = entityClass.getAnnotation(Table.class);

            String schema;
            String catalog;
            String sequenceName;
            if (sequence != null) {
                log.trace("Sequence bulundu.");
                sequenceName = sequence.name();
            } else {
                sequenceName = params.getProperty(JPA_ENTITY_NAME).concat(DEF_SEQUENCE_SUFFIX);
            }

            schema  = tableAnnotation.schema();
            catalog = tableAnnotation.catalog();

            return new QualifiedNameParser.NameParts(Identifier.toIdentifier(catalog),
                                                     Identifier.toIdentifier(schema),
                                                     Identifier.toIdentifier(sequenceName));
        } catch (ClassNotFoundException e) {
            throw new KzRuntimeException(e, new ExceptionMessage("Sequence olusturulamadi", KuartzSequenceGenerator.class));
        }
    }


}
