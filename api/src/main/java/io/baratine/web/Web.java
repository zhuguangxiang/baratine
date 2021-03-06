/*
 * Copyright (c) 1998-2015 Caucho Technology -- all rights reserved
 *
 * This file is part of Baratine(TM)
 *
 * Each copy or derived work must preserve the copyright notice and this
 * notice unmodified.
 *
 * Baratine is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Baratine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, or any warranty
 * of NON-INFRINGEMENT.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Baratine; if not, write to the
 *
 *   Free Software Foundation, Inc.
 *   59 Temple Place, Suite 330
 *   Boston, MA 02111-1307  USA
 *
 * @author Scott Ferguson
 */

package io.baratine.web;

import java.util.Objects;
import java.util.function.Supplier;

import io.baratine.inject.Injector;
import io.baratine.inject.Key;
import io.baratine.service.ServiceRef;
import io.baratine.service.ServiceRef.ServiceBuilder;
import io.baratine.spi.WebServerProvider;
import io.baratine.web.WebServerBuilder.SslBuilder;

/**
 * Web provides static methods to build a web server in a main() class.
 * 
 * <pre><code>
 * import static io.baratine.web.Web.*;
 * 
 * public class MyMain
 * {
 *   public static void main(String []argv)
 *   {
 *     get("/test", req-&gt;req.ok("hello, world"));
 *     
 *     start();
 *   }
 * }
 * </code></pre>
 * 
 * @see RequestWeb
 */
public interface Web
{
  //
  // webserver
  //
  
  static WebServerBuilder port(int port)
  {
    return builder().port(port);
  }
  
  static SslBuilder ssl()
  {
    return builder().ssl();
  }
  
  //
  // routing
  //
  
  static WebServerBuilder include(Class<?> type)
  {
    return builder().include(type);
  }
  
  //
  // view
  //
  
  static <T> WebServerBuilder view(ViewRender<T> view)
  {
    return builder().view(view);
  }
  
  static <T> WebServerBuilder view(Class<? extends ViewRender<T>> view)
  {
    return builder().view(view);
  }
  
  //
  // configuration
  //
  static WebServerBuilder scan(Class<?> type)
  {
    return builder().scan(type);
  }

  static WebServerBuilder scanAutoConf()
  {
    return builder().scanAutoconf();
  }

  /*
  static WebServerBuilder args(String []args)
  {
    return builder().args(args);
  }
  */

  static WebServerBuilder property(String name, String value)
  {
    Objects.requireNonNull(name);
    Objects.requireNonNull(value);
    
    return builder().property(name, value);
  }
  
  //
  // injection
  //
  
  /**
   * Registers a bean for injection.
   * 
   * @param type instance class of the bean
   */
  static <T> Injector.BindingBuilder<T> bean(Class<T> type)
  {
    Objects.requireNonNull(type);
    
    return builder().bean(type);
  }
  
  /**
   * Registers a bean instance for injection.
   */
  static <T> Injector.BindingBuilder<T> bean(T bean)
  {
    Objects.requireNonNull(bean);
    
    return builder().bean(bean);
  }
  
  //
  // services
  //
  
  static <T> ServiceBuilder service(Supplier<? extends T> supplier)
  {
    Objects.requireNonNull(supplier);
    
    //return BaratineWebProvider.builder().service(supplier);
    return null;
  }
  
  static ServiceRef.ServiceBuilder service(Class<?> serviceClass)
  {
    Objects.requireNonNull(serviceClass);
    
    return builder().service(serviceClass);
  }
  
  //
  // routes
  //
  
  static RouteBuilder delete(String path)
  {
    return builder().delete(path);
  }
  
  static RouteBuilder get(String path)
  {
    return builder().get(path);
  }
  
  static RouteBuilder options(String path)
  {
    return builder().options(path);
  }
  
  static RouteBuilder patch(String path)
  {
    return builder().patch(path);
  }
  
  static RouteBuilder post(String path)
  {
    return builder().post(path);
  }
  
  static RouteBuilder put(String path)
  {
    return builder().put(path);
  }
  
  static RouteBuilder trace(String path)
  {
    return builder().trace(path);
  }
  
  static RouteBuilder route(String path)
  {
    return builder().route(path);
  }
  
  static WebSocketBuilder websocket(String path)
  {
    return builder().websocket(path);
  }
  
  //
  // lifecycle
  //
  
  static WebServer start(String ...args)
  {
    return builder().start(args);
  }
  
  static void go(String ...args)
  {
    builder().go(args);
  }
  
  /*
  static void join(String ...args)
  {
    builder().join();
  }
  */
  
  static WebServerBuilder builder()
  {
    return WebServerProvider.current().webBuilder();
  }
}
