# knowledgebase
个人知识库

1、拦截器
![img.png](doc/img.png)
所有⽅法都会执⾏ DispatcherServlet 中的 doDispatch 调度⽅法

![img_1.png](doc/img_1.png)
拦截器可以获取IOC容器中的各个bean，而过滤器就不行，这点很重要，在拦截器里注入一个service，可以调用业务逻辑