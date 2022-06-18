package ru.plesser;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.addAll(List.of(
                new Coffee("Cafe Cereza"),
                new Coffee("Cafe Ganador"),
                new Coffee("Cafe Lareno"),
                new Coffee("Cafe Tres Pontas")
        ));
    }

    @RequestMapping(value = "/coffees", method = RequestMethod.GET)
    Iterable<Coffee> getCoffees(){
        return coffees;
    }

    @GetMapping(value = "/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id){
        for (Coffee c : coffees){
            if (c.getId().equals(id)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/coffees")
    Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/coffee/{id}")
    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        int coffeeIndex = -1;

        for (Coffee c: coffees){
            if (c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
    }

    @DeleteMapping("/coffee/{id}")
    void deleteCoffee(@PathVariable String id){
        coffees.removeIf(c -> c.getId().equals(id));
    }


}
