package com.codewithdurgesh.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
	private Integer categoryId;
	
	@NotBlank
	@Size(min=4,message = "Min size must be 4")
	private String categoryTittle;
	
	@NotBlank
	@Size(max=10,message = "Max size must be 10")
	private String categoryDescription;

}
