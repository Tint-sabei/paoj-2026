package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private OrderState currentState;
    private final List<OrderState> process = new ArrayList<>();

    public Order(OrderState initialState) {
        this.currentState = initialState;
        this.process.add(initialState);
    }

    public void nextState() throws OrderIsAlreadyFinalException {
        if (currentState.isFinal()) {
            throw new OrderIsAlreadyFinalException();
        }
        currentState = currentState.next();
        process.add(currentState);
        System.out.println("Order state updated to: " + currentState);
    }

    public void cancel() throws CannotCancelFinalOrderException {
        if (currentState.isFinal()) {
            throw new CannotCancelFinalOrderException();
        }
        currentState = OrderState.CANCELED;
        process.add(currentState);
        System.out.println("Order has been canceled.");
    }

    public void undoState() throws CannotRevertInitialOrderStateException {
        if (process.size() <= 1) {
            throw new CannotRevertInitialOrderStateException();
        }

        int lastIndex = process.size() - 1;
        process.remove(lastIndex);

        int previousIndex = process.size() - 1;
        this.currentState = process.get(previousIndex);

        System.out.println("Order state reverted to: " + currentState);
    }
}