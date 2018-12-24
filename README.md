###spring-dubbo-zookeeper
1.添加依赖   
 
	<dependencies>
    <dependency>
      <groupId>com.101tec</groupId>
      <artifactId>zkclient</artifactId>
      <version>0.9</version>
    </dependency>
    <dependency>
      <groupId>org.apache.zookeeper</groupId>
      <artifactId>zookeeper</artifactId>
      <version>3.4.9</version>
      <type>pom</type>
    </dependency>
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>dubbo</artifactId>
      <version>2.5.3</version>
    </dependency>
    <dependency>
      <groupId>io.netty</groupId>
      <artifactId>netty-all</artifactId>
      <version>4.1.6.Final</version>
    </dependency>
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.2</version>
    </dependency>
    <dependency>
      <groupId>org.javassist</groupId>
      <artifactId>javassist</artifactId>
      <version>3.21.0-GA</version>
    </dependency>
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>
    <!-- spring相关jar -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.3.3.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>4.3.3.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.3.18.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>4.3.3.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>4.3.3.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>4.3.3.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>4.3.3.RELEASE</version>
    </dependency>


    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>

  </dependencies>

2.提供者  
(1)applicationContext-provider.xml    

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns:context="http://www.springframework.org/schema/context"
			xmlns:aop="http://www.springframework.org/schema/aop"
			xmlns:tx="http://www.springframework.org/schema/tx"
			xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
			xsi:schemaLocation="
			http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
			http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
			http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
			http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd
			"
			>
		<dubbo:application name="provider-app" />
		<!--使用zookeeper进行注册中心配置-->
		<dubbo:registry address="zookeeper://localhost:2181" />
	
		<dubbo:protocol name="dubbo" port="20880"/>
	
		<!--<dubbo:service registry="N/A" interface="com.dong.dubbo.ServiceAPI" ref="quickStartService" />-->
	
		<dubbo:service interface="com.dong.dubbo.ServiceAPI" ref="quickStartService" />
	
		<bean id="quickStartService" class="com.dong.dubbo.quickStart.QuickStartServiceImpl" />
	
	</beans>

(2) 写一个接口  
  
	public interface ServiceAPI {
	
	    String sendMsg(String message);
	
	}  
(3)写一个实现类  
  
	public class QuickStartServiceImpl implements ServiceAPI{
	    @Override
	    public String sendMsg(String message) {
	        return "provider-send-message="+message;
	    }
	}
(4)写一个main方法  
  
	public class ProviderClient {
	
	    public static void main(String[] args) {
	        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-provider.xml");
	        applicationContext.start();
	
	        try {
	            System.in.read();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}  
2.消费者  
  
(1)applicationContext-consumer.xml  
  
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns:context="http://www.springframework.org/schema/context"
			xmlns:aop="http://www.springframework.org/schema/aop"
			xmlns:tx="http://www.springframework.org/schema/tx"
			xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
			xsi:schemaLocation="
			http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
			http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
			http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
			http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd
			"
			>
	
			<dubbo:application name="consumer-app" />
	
			<!--使用zookeeper进行注册中心配置-->
			<dubbo:registry address="zookeeper://localhost:2181" />
	
			<dubbo:reference interface="com.dong.dubbo.ServiceAPI" id="consumerService" />
		<!--<dubbo:reference interface="com.dong.dubbo.ServiceAPI" id="consumerService" url="dubbo://localhost:20880" />-->
	
	
	
	</beans>
(2)写一个和提供者一样的接口  
  
	public interface ServiceAPI {
	
	     String sendMsg(String message);
	
	}  
(3)写一个main方法  
  
	public class ConsumerClient {
	    public static void main(String[] args) {
	        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-consumer.xml");
	        context.start();
	
	        while (true) {
	            Scanner scanner = new Scanner(System.in);
	            String message = scanner.next();
	
	            //获取接口
	            ServiceAPI serviceAPI = (ServiceAPI) context.getBean("consumerService");
	            System.out.println(serviceAPI.sendMsg(message));
	        }
	    }
	
	}
