package com.company;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//        Issue issue = new Issue("App flow tests?", "We need to test the App!", tomorrow);
//        Task task = new Task("Test the application flow", "Pesho", tomorrow);
//
//// this MUST not compile: "'BoardItem' is abstract; cannot be instantiated"
//        BoardItem item = new BoardItem("title", tomorrow);


//        -----------------------------------------------------------------------------

//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//        Issue issue = new Issue("App flow tests?", "We need to test the App!", tomorrow);
//
//        issue.revertStatus();
//        issue.advanceStatus();
//        issue.advanceStatus();
//        issue.revertStatus();
//
//        issue.displayHistory();

//        -------------------------------------------------------------------

//        LocalDate tomorrow = LocalDate.now().plusDays(1);
//        Task task = new Task("App flow tests?", "Pesho", tomorrow);
//
//        task.revertStatus();
//        task.advanceStatus();
//        task.advanceStatus();
//        task.revertStatus();
//        task.advanceStatus();
//        task.advanceStatus();
//        task.advanceStatus();
//
//        task.displayHistory();

//        ----------------------------------------------------------------

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        Task task = new Task("App flow tests?", "Pesho", tomorrow);
        Issue issue = new Issue("Review tests", "Someone must review Pesho's tests.", tomorrow);

        Board board = new Board();

        board.addItem(task);
        board.addItem(issue);
        task.advanceStatus();
        issue.advanceStatus();

        board.displayHistory();


    }

}
