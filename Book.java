package application;

import java.io.Serializable;

public class Book implements Serializable {
	public int code;
	public String title;
	public String author;
	public String category;
	public String condition;
	public double price;
	public int chosen_number;

		Book(){
			
		}
		
		Book(int code, String title, String author, String category, 
				String condition, float price, int num){
			this.code = code;
			this.title = title;
			this.author = author;
			this.category = category;
			this.condition = condition;
			this.price = price;
			this.chosen_number = num;
			
		}
		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getCondition() {
			return condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double newPrice) {
			this.price = newPrice;
		}

		public int getChosen_number() {
			return chosen_number;
		}

		public void setChosen_number(int chosen_number) {
			this.chosen_number = chosen_number;
		}
		public String toString() {
		        return (title + " by " + author + " Price: $"+price+" Condition: "+condition);
		    }

		
}
