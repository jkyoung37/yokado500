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

import javax.validation.Valid;

import com.jp.yokado.form.ReceiptForm;
import com.jp.yokado.model.Receipt;
import com.jp.yokado.utils.*;
import com.jp.yokado.validation.Validations;

@Controller
@RequestMapping("/point")
public class PointController {

	@ModelAttribute
	public ReceiptForm setupForm() {
		return new ReceiptForm();
	}

	@GetMapping
	public String init(ReceiptForm form) {
		form.appendRow();
		form.setMaxPoint(Numbers.MAX_POINT);
		return "inputPointPage";
	}

	@PostMapping(params = "init")
	public String moveInit(ReceiptForm form, BindingResult result) {
		form.appendRow();
		return "inputPointPage";
	}
	
	@PostMapping(params = "goBefore")
	public String moveBefore() {
	      return "redirect:/";
	}

	@PostMapping(params = "appendRow")
	public String appendRow(ReceiptForm form, BindingResult result) {
		form.appendRow();
		return "inputPointPage";
	}

	@PostMapping(params = "removeIndex")
	public String submit(ReceiptForm form, @RequestParam int removeIndex, BindingResult result) {
		form.removeRow(removeIndex);
		return "inputPointPage";
	}

	@PostMapping(params = "doCalculate")
	public String submit(Model model, @Valid ReceiptForm form, BindingResult result) {

		if (result.hasErrors()) {
			return "inputPointPage";
		}

		List<ArrayList<Integer>> minSumResult = new ArrayList<>();
		Numbers.MAX_POINT = form.getMaxPoint();
		boolean isOverMaxpoint = Utils.OverMaxPoint(form.getReceipts());

		if (isOverMaxpoint) {
			minSumResult = this.getMinSumResult(this.getAllPoint(form));
			model.addAttribute("results", this.getFinalResult(minSumResult));
			return "resultPointPage";

		} else {
			return "inputPointPage";
		}

	}

	private List<ArrayList<Integer>> getMinSumResult(List<ArrayList<Integer>> items) {
		Integer minSum = 0;
		List<ArrayList<Integer>> result = new ArrayList<>();
		ArrayList<Integer> initValue = new ArrayList<>();

		for (ArrayList<Integer> item : items) {
			int sum = item.stream().mapToInt(Integer::intValue).sum();

			if (sum >= Numbers.MAX_POINT) {
				if (sum <= minSum.intValue() && minSum.intValue() > 0) {
					minSum = sum;
					result.add(item);
				}

				if (minSum == 0) {
					minSum = sum;
					initValue = item;
				}
			}
		}
		if (result.size() == 0 && minSum > 0) {
			result.add(initValue);
		}
		return result;
	}

	public List<String> getFinalResult(List<ArrayList<Integer>> items) {
		List<String> result = new ArrayList<>();

		for (ArrayList<Integer> item : items) {
			String joinedString = item.stream().map(x -> x.toString()).collect(Collectors.joining("・", "・", " "));
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
}
