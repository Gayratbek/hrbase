//package uz.coding.employee.controller.router;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//import uz.coding.employee.dto.Person;
//import uz.coding.employee.service.PersonalService;
//
//@RestController
//@RequiredArgsConstructor
//public class PersonController {
//
//    private final PersonalService personalService;
//
//    @GetMapping("/api/personal/")
//    public Flux<Person> personalInformation(){
//        return personalService.find();
//    }
//}
