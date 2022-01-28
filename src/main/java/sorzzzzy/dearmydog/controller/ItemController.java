package sorzzzzy.dearmydog.controller;

import sorzzzzy.dearmydog.domain.Item.Food;
import sorzzzzy.dearmydog.domain.Item.Item;
import sorzzzzy.dearmydog.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // 상품 등록 - GET
    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new FoodForm());
        return "items/createItemForm";
    }

    // 상품 등록 - POST
    @PostMapping("/items/new")
    public String create(FoodForm form) {

        Food food = new Food();
        food.setName(form.getName());
        food.setPrice(form.getPrice());
        food.setStockQuantity(form.getStockQuantity());
        food.setAllowance(form.getAllowance());
        food.setIngredient(form.getIngredient());

        itemService.saveItem(food);
        return "redirect:/";
    }

    // 상품 목록 조회
    @GetMapping("/items")
    public String list(Model model) {
        // findItems() 로 찾아온 뒤, modelAttribute 사용
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    // 상품 수정
    // itemId는 변경될 수 있으므로 @PathVariable 사용
    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        // 캐스팅을 하는 것이 좋은 방법은 아니나, 여기서는 예제 단순화를 위해 사용
        Food item = (Food) itemService.findOne(itemId);

        // 폼을 업데이트 할 건데, 엔티티가 아닌 BookForm 을 보냄
        FoodForm form = new FoodForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAllowance(item.getAllowance());
        form.setIngredient(item.getIngredient());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    // @PathVariable Long itemId : 폼에서 그대로 넘어오기 때문에 생략해도 됨
    // @ModelAttribute("form") : 폼에서 오브젝트 이름으로 설정한 부분이 그대로 넘어옴
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") FoodForm form) {

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}





