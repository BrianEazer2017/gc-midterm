package midtermpackage;

public class Toy {
	private String name; 
	private String category;
	private double price;
	private String description;
	
	public Toy () {}
	
	public Toy(String name, String category, double price, String description) {
		super();
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		
		
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

	@Override
	public String toString() {
		return "Toy [name=" + name + ", category=" + category + ", price=" + price + ", description=" + description
				+ "]";
	}

}
