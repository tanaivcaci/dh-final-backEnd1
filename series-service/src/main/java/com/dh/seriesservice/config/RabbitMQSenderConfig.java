package com.dh.seriesservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

   /* Value para obtener la cola que necesito referenciar */
   @Value ("${queue.catalog.name}")
   private String movieQueueCatalog;

   /* Para linkear la queue, la cree en rabbitMQ y sea funcional */
   @Bean
   public Queue queue(){
      return new Queue (movieQueueCatalog, true);
   }
}
