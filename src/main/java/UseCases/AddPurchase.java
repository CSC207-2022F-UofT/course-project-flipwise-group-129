package UseCases;

import DataStructures.PurchaseInfo;
import DataStructures.UpdatedLists;
import Entities.*;
import InputBoundary.AddPurchaseBoundaryIn;
import OutputBoundary.AddPurchaseBoundaryOut;
import Presenters.AddPurchasePresenter;
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

        this.purchasedItem = purchaseInfo.getItem();
        this.participatingUsers = purchaseInfo.getUsers();
        this.price = purchaseInfo.getPrice();
        this.purchaseGroup = purchaseInfo.getPurchaseGroup();
        this.buyer = purchaseInfo.getBuyer();
        this.presenter = purchaseInfo.getPresenter();

        PlanningList planningList = this.purchaseGroup.getPlanningList();
        planningList.removeFromList(this.purchasedItem);

        PurchaseList purchaseList = this.purchaseGroup.getPurchaseList();
        this.purchasedItem.setPrice(this.price);
        this.purchasedItem.setBuyer(this.buyer);
        purchaseList.addItems(this.purchasedItem);

        newLists = new UpdatedLists(this.purchaseGroup.getPlanningList(), this.purchaseGroup.getPurchaseList());

        presenter.updateView(newLists);
    }
}
