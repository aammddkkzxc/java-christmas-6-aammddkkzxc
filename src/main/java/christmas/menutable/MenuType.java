package christmas.menutable;

import java.util.Arrays;
import java.util.List;

public enum MenuType {
    APPETIZER("애피타이저", Arrays.asList(Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN("메인", Arrays.asList(Menu.T_BONE_STAKE, Menu.BARBECUE_LIP, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트", Arrays.asList(Menu.CHOCOLATE_CAKE, Menu.ICE_CREAM)),
    BEVERAGE("음료", Arrays.asList(Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE));

    private final String name;
    private final List<Menu> menus;

    MenuType(String name, List<Menu> menus) {
        this.name = name;
        this.menus = menus;
    }

    public static MenuType decideMenuType(Menu menu) {
        MenuType[] menuTypes = MenuType.values();

        for (MenuType menuType : menuTypes) {
            if (menuType.getMenus().contains(menu)) {
                return menuType;
            }
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}