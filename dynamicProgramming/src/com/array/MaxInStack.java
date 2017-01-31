package com.array;

import java.util.Stack;

public class MaxInStack {

	Stack<Integer> mainStack = new Stack<>();
	Stack<Integer> maxStack = new Stack<>();

	public void push(Integer element) {
		if (maxStack.isEmpty()) {
			maxStack.push(element);
		} else {
			maxStack.push(Math.max(maxStack.peek(), element));
		}
		mainStack.push(element);
	}

	public Integer pop() throws Exception {
		if (maxStack.isEmpty()) {
			throw new Exception("Stack is empty");
		}
		maxStack.pop();
		return mainStack.pop();
	}

	public Integer getMaxElement() throws Exception {
		if (maxStack.isEmpty()) {
			throw new Exception("Stack is empty");
		}
		return maxStack.peek();
	}

	public static void main(String[] args) throws Exception {
		MaxInStack maxStack = new MaxInStack();
		maxStack.push(10);
		System.out.println("Max in stack is - " + maxStack.getMaxElement());
		maxStack.push(15);
		System.out.println("Max in stack is - " + maxStack.getMaxElement());
		maxStack.push(12);
		System.out.println("Max in stack is - " + maxStack.getMaxElement());
		maxStack.push(9);
		System.out.println("Max in stack is - " + maxStack.getMaxElement());

		maxStack.pop();
		System.out.println("Max in stack is - " + maxStack.getMaxElement());
		maxStack.pop();
		System.out.println("Max in stack is - " + maxStack.getMaxElement());
		maxStack.pop();
		System.out.println("Max in stack is - " + maxStack.getMaxElement());

	}

}
