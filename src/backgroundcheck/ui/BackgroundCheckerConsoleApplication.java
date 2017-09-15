package backgroundcheck.ui;

import backgroundcheck.*;
import org.reflections.Reflections;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class BackgroundCheckerConsoleApplication
{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        FullBackGroundCheck currentCheck = new FullBackGroundCheck();

        printEvaluationResults(promptUserForCandidateFullName(), currentCheck.runBackgroundCheck(promptUserForCandidate(), promptForCriteria()));
    }

    private static String promptUserForCandidateFullName()
    {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter a candidate's first name");
        String firstName = userInput.next();

        System.out.println("Enter the candidate's last name");
        String lastName = userInput.next();

        return firstName + " " + lastName;
    }

    private static List<CriteriaChecker> promptForCriteria() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<CriteriaChecker> criteriaForBackgroundCheck = new ArrayList<>();
        showCriteriaMenu();
        List<Integer> userInputList= getUserInput();

        for (int selection : userInputList){
            criteriaForBackgroundCheck.add((CriteriaChecker) Class.forName(retrieveClassList().get(selection - 1).substring(6)).newInstance());
        }

        return criteriaForBackgroundCheck;
    }

    private static List<String> retrieveClassList()
    {
        Reflections reflections = new Reflections("backgroundcheck");

        Set<Class<? extends CriteriaChecker>> allClasses =
                reflections.getSubTypesOf(CriteriaChecker.class);

        return allClasses.stream().map(Class::toString).collect(Collectors.toList());
    }

    private static void showCriteriaMenu()
    {
        System.out.println("Choose A Service");

        retrieveClassList().forEach(stringInList -> System.out.println((retrieveClassList().indexOf(stringInList)+1) + ". " + stringInList.substring(22)));

        System.out.println("Or enter 0 if no further criteria desired");

    }

    private static Candidate promptUserForCandidate()
    {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the candidate's social");

        return new Candidate(userInput.nextInt());
    }

    private static void printEvaluationResults(String fullName, List<String> reasonsForFailure)
    {
        System.out.println("Candidate: " + fullName);
        if (!reasonsForFailure.isEmpty()) {
            System.out.println("Rejected for the following reasons: ");
            reasonsForFailure.forEach(System.out::println);
        } else
            System.out.println("Approved");
    }

    public static List<Integer> getUserInput()
    {
        List<Integer> userInputList= new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        int input;
        do
        {
            input = userInput.nextInt();
            if (!userInputList.contains(input) && input != 0)
                userInputList.add(input);
        }
        while(input != 0);

        return userInputList;
    }
}
