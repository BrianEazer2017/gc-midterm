package midtermpackage;

public class Toy {
	private String name; 
	private String category;
	private double price;
	private String description;
	private int quantity;
	private int inventory;
	
	public Toy () {}
	
	public Toy(String name, String category, double price, String description,  int quantity, int inventory) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.inventory = inventory;
		
		
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "Toy [name=" + name + ", category=" + category + ", price=" + price + ", description=" + description
				+ ", quantity" + quantity + "]";
	}

}
