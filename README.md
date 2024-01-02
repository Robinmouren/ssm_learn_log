# SSM入门案例学习日志 ————2024-1-2
## 后端使用的技术栈
* service接口层的接口方法按规范添加注释
* webapp包下的前端文件想要被识别需定义一个配置类继承WebMvcConfigurationSupport，
并覆写void addResourceHandlers(ResourceHandlerRegistry registry)方法，
使用registry对象调用addResourceHandler方法声明需要被识别的路径，
再调用addResourceLocations方法声明从哪个包下扫描，并在类上添加@Configuration注释

* REST风格
    * 对于表现层前后端数据交互方法的实现，通常在类的上面使用@Controller注释，
    在方法上使用@RequestMapping注释添加请求路径以及@ResponseBody注释
    将返回数据整体当作响应体
    * 但此时时请求路径会透露太多操作信息:
        * 对book路径下进行crud的操作可能会请求"/book/get"、"/book/get/1"、"/book/save"、
        "/book/update/1"、"/book/delete/1"
    * 使用不同请求方式和不同参数以表示不同的请求方法:
        * /books    -get        @RequestBody Book book
        * /books/1  -get        @PathVariable Integer id
        * /books    -post       @RequestBody Book book
        * /books/1  -put        @PathVariable Integer id
        * /books/1  -delete     @PathVariable Integer id
    * 在@RequestMapping的method属性中加入RequestMethod.GET等方法
    * 将@ResponseBody去掉并将@Controller改成@RestController(如果方法中无跳转页面)
    * 路径默认使用对象后加s的风格，例：/books
    * 简化开发:直接使用对应的Mapping方法而不是RequestMapping
    
* Result  Code
    * 后端返回给前端的数据可能有很多种不同类型：boolean、String、json、页面等，此时对于前端开发者
    而言处理很麻烦，所以与后端和前端对于返回数据类型的同意很有必要
    * 定义Result类
    * 将放回数据放如Object类型的data中，但此时若前端进行的操作无返回值，此时data为null，
    此时不能判断操作是否执行成功
    * 定义一个Integer类型的code，用于表示返回状态，此时若既无返回值，状态码也是错误的，则
    前端还需要向用户返回一个消息
        * 定义一个Code类，在其中定义静态常量状态码，方便使用
    * 定义一个String类型的message，用于表示提示信息
* 异常处理
    * 程序在运行时出现程序运行错误或者系统异常，甚至是无法预料的错误时，
    后端无法返回给前端返回数据，此时需要将异常拦截并向前端返回数据
    * 对于在表现层写的任何一个方法都有可能出现异常，所以在每一个方法中使用
    try..catch并不适用
    * 既然是所有的方法都有可能出现异常，那么就是对所有方法进行异常的捕捉，
    也就是对所有方法实现方法的增强，可以使用AOP的思想
    * 在表现层定义一个类使用@RestControllerAdvice注解，定义方法并在上面使用
    @ExceptionHandler(_异常类型_)注解用于拦截指定类型的异常，返回值
    new Result对象并将状态码、异常对象、message信息等根据构造器填入，此时
    若前端请求出现异常，这个异常拦截方法会拦截异常并将结果放回给前端
    * 同时可以定义不同异常类继承RuntimeException，在其中覆盖两个构造器，
    则可以在表现层中的异常捕捉类中进行捕捉
        * [自定义异常为什么要继承RuntimeException](https://blog.csdn.net/weixin_49302930/article/details/131558728?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522170416613216800227443039%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=170416613216800227443039&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-2-131558728-null-null.142^v99^pc_search_result_base2&utm_term=RuntimeException%E7%BB%A7%E6%89%BF&spm=1018.2226.3001.4187)

