package UseCases;

import DataAccessInterface.GroupDataInterface;
import DataAccessInterface.ItemDataInterface;
import DataAccessInterface.UserDataInterface;
import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.*;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
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
    private GroupDataInterface groupData;
    private ItemDataInterface itemData;

    @Override
    public void executeUseCase(PurchaseInfo purchaseInfo) {
        this.purchaseInfo = purchaseInfo;

        extractInformation(purchaseInfo);

        PlanningList planningList = this.purchaseGroup.getPlanningList();
        planningList.removeFromList(this.purchasedItem);

        PurchaseList purchaseList = this.purchaseGroup.getPurchaseList();
        addToPurchase(purchaseList);

        List<String> planningListItemIds = convertList(planningList);
        List<String> purchasedListItemIds = convertList(purchaseList);
        newLists = new UpdatedLists(planningListItemIds, purchasedListItemIds);

        writeData();

        this.presenter.updateView(newLists);
    }

    private void writeData() {
        try {
            groupData.addorUpdateGroup(this.purchaseInfo.getPurchaseGroup(), this.purchaseGroup.toString());
            itemData.addorUpdateItem(this.purchaseInfo.getItem(), this.purchasedItem.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addToPurchase(PurchaseList purchaseList) {
        this.purchasedItem.setPrice(this.price);
        this.purchasedItem.setBuyer(this.buyer);
        purchaseList.addItems(this.purchasedItem);
    }

    private void extractInformation(PurchaseInfo purchaseInfo) {
        try {
            this.purchasedItem = Item.fromString(purchaseInfo.getItem());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        extractUsers(purchaseInfo);
        this.price = purchaseInfo.getPrice();
        try {
            this.purchaseGroup = Group.fromString(groupData.groupAsString(purchaseInfo.getPurchaseGroup()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            this.buyer = User.fromString(purchaseInfo.getBuyer());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.presenter = purchaseInfo.getPresenter();
        this.groupData = purchaseInfo.getGroupData();
        this.itemData = purchaseInfo.getItemData();

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
