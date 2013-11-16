@RestController
class TsaController {
    
    @RequestMapping("/")
    String index() {
        return "All the Data!";
    }
    
}