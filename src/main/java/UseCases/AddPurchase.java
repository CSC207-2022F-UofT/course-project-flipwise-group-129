package UseCases;

import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.*;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;

import java.util.ArrayList;
import java.util.List;

public class AddPurchase implements AddPurchaseBoundaryIn {
    private PurchaseInfo purchaseInfo;
    private Item purchasedItem;
    private List<User> participatingUsers;
    private float price;
    private Group purchaseGroup;
    private UpdatedLists newLists;
    private User buyer;
    private AddPurchaseBoundaryOut presenter;

    @Override
    public void executeUseCase(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;

        this.purchasedItem = getItemById(purchaseInfo.getItem());
        extractUsers(purchaseInfo);
        this.price = purchaseInfo.getPrice();
        this.purchaseGroup = getGroupById(purchaseInfo.getPurchaseGroup());
        this.buyer = getUserByUsername(purchaseInfo.getBuyer());
        this.presenter = purchaseInfo.getPresenter();

        PlanningList planningList = this.purchaseGroup.getPlanningList();
        planningList.removeFromList(this.purchasedItem);

        PurchaseList purchaseList = this.purchaseGroup.getPurchaseList();
        this.purchasedItem.setPrice(this.price);
        this.purchasedItem.setBuyer(this.buyer);
        purchaseList.addItems(this.purchasedItem);

        List<String> planningListItemIds = convertList(planningList);
        List<String> purchasedListItemIds = convertList(purchaseList);
        newLists = new UpdatedLists(planningListItemIds, purchasedListItemIds);
        presenter.updateView(newLists);
    }

    private void extractUsers(PurchaseInfo purchaseInfo) {
        List<String> usernames = purchaseInfo.getUsers();
        for (String username : usernames) {
            this.participatingUsers.add(getUserById(username));
        }
    }

    public List<String> convertList(ItemList inputList) {
        List<Item> tempListItems = inputList.getItems();
        List<String> tempListItemIds = new ArrayList<String>();
        for (Item item: tempListItems) {
            tempListItemIds.add(item.id);
        }
        return tempListItemIds;
    }
}
