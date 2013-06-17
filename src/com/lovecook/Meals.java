package com.lovecook;

public class Meals {
	
	private Integer _id;
	private String number;
	private String category;
	private String name;
	private String recipe;
	private String preparation;
	
	
	
	public Meals() {
		super();
	}

	public Meals(String name, String recipe, String preparation) {
		super();
		this.name = name;
		this.recipe = recipe;
		this.preparation = preparation;
	}
	
	public Meals(String number) {
		super();
		this.number = number;
	}

	public Meals(Integer _id, String number) {
		super();
		this._id = _id;
		this.number = number;
	}

	public Meals(Integer _id, String number, String category, String name,
			String recipe, String preparation) {
		super();
		this._id = _id;
		this.number = number;
		this.category = category;
		this.name = name;
		this.recipe = recipe;
		this.preparation = preparation;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public String getPreparation() {
		return preparation;
	}

	public void setPreparation(String preparation) {
		this.preparation = preparation;
	}

	@Override
	public String toString() {
		return "_id=" + _id + ", number=" + number + ", category="
				+ category + ", name=" + name + "recipe=" + recipe
				+ ", preparation=" + preparation;
	}	
	
	

}
