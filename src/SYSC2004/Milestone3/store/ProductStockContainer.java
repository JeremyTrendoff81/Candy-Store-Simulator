package SYSC2004.Milestone3.store;

public interface ProductStockContainer {
    public abstract int getProductQuantity(Product product);
    public abstract void addProductQuantity(Product product, int quantity);
    public abstract boolean removeProductQuantity(Product product, int quantity);
    public abstract int getNumOfProducts();
}
