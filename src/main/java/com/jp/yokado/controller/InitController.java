package com.jp.yokado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jp.yokado.form.ReceiptForm;
import com.jp.yokado.model.Receipt;
import com.jp.yokado.utils.*;
import com.jp.yokado.validation.Validations;

@Controller
@RequestMapping("/init")
public class InitController {

	@ModelAttribute
	public ReceiptForm setupForm() {
		return new ReceiptForm();
	}

	@GetMapping
	public String init(ReceiptForm form) {
		form.appendRow();
		return "index";
	}
	
	@PostMapping(params = "init")
	public String moveInit(ReceiptForm form, BindingResult result) {
		form.appendRow();
		return "index";
	}

	@PostMapping(params = "appendRow")
	public String appendRow(ReceiptForm form, BindingResult result) {
		form.appendRow();
		return "index";
	}

	@PostMapping(params = "removeIndex")
	public String submit(ReceiptForm form, @RequestParam int removeIndex, BindingResult result) {
		form.removeRow(removeIndex);
		return "index";
	}

	@PostMapping(params = "doCalculate")
	public String submit(Model model, ReceiptForm form, BindingResult result) {

		if (result.hasErrors()) {
			return "index";
		}

		if (this.hasErrors(form)) {
			return "index";
		}

		List<ArrayList<Integer>> allPoints = new ArrayList<>();
		List<ArrayList<Integer>> minSumResult = new ArrayList<>();

		
		Integer minSum = 0;

		boolean isOverMaxpoint = Utils.OverMaxPoint(form.getReceipts());
		
		if (isOverMaxpoint) {
			allPoints = this.getAllPoint(form);
			for (ArrayList<Integer> items : allPoints) {
				int sum = items.stream().mapToInt(Integer::intValue).sum();
				if (sum >= Numbers.MAX_POINT) {
					if (sum <= minSum.intValue() || minSum.intValue() == 0) {
						minSum = sum;
						minSumResult.add(items);
					}
				}
			}
			model.addAttribute("results",this.getFinalResult(minSumResult));
			return "resultPage";
		
		} else {
			return "index";
		}
		

	}
	
	public List<String> getFinalResult(List<ArrayList<Integer>> items){
		
		List<String> result = new ArrayList<>();
		
		for(ArrayList<Integer> item : items) {
			String joinedString = item.stream().map(x -> x.toString()).collect(Collectors.joining("・","・"," "));
			result.add(joinedString);
		}
		return result;
	}
	
	private List<ArrayList<Integer>> getAllPoint(ReceiptForm form) {
		List<ArrayList<Integer>> result = new ArrayList<>();
		List<Receipt> itemLists = form.getReceipts();
		int[] points = Utils.convertIntegers(itemLists);
		int n = points.length;
		boolean[] visited = new boolean[n];

		Combination.clear();
		for (int i = 1; i <= n; i++) {
			Combination.combination(points, visited, 0, n, i);
		}

		List<int[]> tempLists = Combination.getItem();

		for (int[] items : tempLists) {
			List<Integer> tempList = Arrays.stream(items).boxed().collect(Collectors.toList());
			result.add((ArrayList<Integer>) tempList);
		}

		return result;
	}

	private boolean hasErrors(ReceiptForm form) {
		boolean isNull = Validations.isNull(form.getReceipts());
		if (isNull) {
			return true;
		}
		return false;
	}
}
