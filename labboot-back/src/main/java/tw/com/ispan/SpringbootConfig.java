// package tw.com.ispan;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class SpringbootConfig implements WebMvcConfigurer {
//     /**
//      * 配置跨域资源共享(CORS)映射
//      * @param registry CORS注册表，用于添加跨域映射规则
//      */
//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         // 添加第一个跨域映射规则，允许对"/ajax/secure/**"路径的跨域访问
//         registry.addMapping("/ajax/secure/**");
//         registry.addMapping("/ajax/pages/products/**")
//                 .allowedMethods("GET", "POST", "PUT", "DELETE");
//     }
// }
