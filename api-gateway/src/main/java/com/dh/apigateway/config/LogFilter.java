package com.dh.apigateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.logging.Logger;

@Component
public class LogFilter extends AbstractGatewayFilterFactory<LogFilter.Config> {

   private static Logger LOG = Logger.getLogger (LogFilter.class.getName ());

   public static class Config {

   }

   public LogFilter(){
      super(Config.class);
   }

   @Override
   public GatewayFilter apply (Config config) {
      return (exchange, chain) -> {
         //Filtro previo a la request del service asociado al gateway
         LOG.info ("Path requested: " + exchange.getRequest ().getPath ());
         LOG.info ("La request vino desde la uri: " + exchange.getRequest ().getURI ());
         LOG.info ("La request vino desde el puerto: " + exchange.getRequest ().getHeaders ().get ("port"));

         return chain.filter (exchange).then (Mono.fromRunnable (()->{
            //Filtro posterior a la request del service asociado al gateway
            LOG.info ("Time Response: " + Calendar.getInstance ().getTime ());
            LOG.info ("La request BLAAAA  PUERTO: " + exchange.getRequest ().getHeaders ().get ("port"));
         }));
      };
   }
}
