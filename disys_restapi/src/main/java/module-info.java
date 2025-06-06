module disys_restapi {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.web;
    requires spring.core;
    requires com.fasterxml.jackson.annotation;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens at.fhtechnikum.disys_restapi to spring.core, spring.beans, spring.data.jpa, org.hibernate.orm.core;
    exports at.fhtechnikum.disys_restapi;
}