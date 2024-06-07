import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Definizione della servlet
@WebServlet("/Hello")
public class Hello extends HttpServlet {
    
    // Variabili di istanza per memorizzare l'input corrente, i numeri e l'operatore
    private String currentInput;
    private String currentNumber1;
    private String currentNumber2;
    private String currentOperator;
    private String errorMessage;

    // Metodo doPost per gestire le richieste POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupero l'input dall'utente
        String input = request.getParameter("input");

        // Inizializzo le variabili se è la prima volta che vengono usate
        if (currentInput == null) {
            currentInput = "";
            currentNumber1 = "";
            currentNumber2 = "";
            currentOperator = "";
            errorMessage = "";
        }

        // Se l'input non è vuoto, lo elaboro
        if (input != null && !input.isEmpty()) {
        	GestioneInput(input, request);
        }
        
        // Imposto gli attributi della richiesta per poterli utilizzare nella JSP
        request.setAttribute("currentInput", currentInput);
        request.setAttribute("currentNumber", currentNumber1);
        request.setAttribute("currentOperator", currentOperator);
        request.setAttribute("errorMessage", errorMessage);
        
        // Inoltro la richiesta alla JSP
        request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
    }

    // Metodo per gestire l'input dell'utente
    private void GestioneInput(String input, HttpServletRequest request) {
        switch (input) {
            case "C":  // Se l'input è "C", resetto tutto
                currentInput = "";
                currentNumber1 = "";
                currentNumber2 = "";
                currentOperator = "";
                errorMessage = "";
                break;
            case "+":
            case "-":
            case "*":
            case "/":  // Se l'input è un operatore, calcolo il risultato intermedio e memorizzo l'operatore
                if (!currentNumber1.isEmpty() && !currentNumber2.isEmpty() && !currentOperator.isEmpty()) {
                    try {
                        double result = Calc(Double.parseDouble(currentNumber1), currentOperator, Double.parseDouble(currentNumber2));
                        currentNumber1 = String.valueOf(result);
                        currentNumber2 = "";
                        currentOperator = input;
                        currentInput = currentOperator;
                        errorMessage = "";
                    } catch (ArithmeticException e) {
                        errorMessage = "Errore: " + e.getMessage();
                    } catch (NumberFormatException e) {
                        errorMessage = "Errore: Formato di input non valido";
                    }
                } else if (!currentNumber1.isEmpty()) {
                    currentOperator = input;
                    currentInput = currentOperator;
                    errorMessage = "";
                }
                break;
            case "=":  // Se l'input è "=", calcolo il risultato finale
                if (!currentNumber1.isEmpty() && !currentNumber2.isEmpty() && !currentOperator.isEmpty()) {
                    try {
                        double result = Calc(Double.parseDouble(currentNumber1), currentOperator, Double.parseDouble(currentNumber2));
                        currentInput = String.valueOf(result);
                        currentNumber1 = String.valueOf(result);
                        currentOperator = "";
                        currentNumber2 = "";
                        errorMessage = "";
                    } catch (ArithmeticException e) {
                        errorMessage = "Errore: " + e.getMessage();
                    } catch (NumberFormatException e) {
                        errorMessage = "Errore: Formato di input non valido";
                    }
                }
                break;
            default:  // Se l'input è un numero, lo aggiungo al numero corrente
                if (isNumeric(input)) {
                    if (currentOperator.isEmpty()) {
                        currentNumber1 += input;
                        currentInput = currentNumber1;
                    } else {
                        currentNumber2 += input;
                        currentInput = currentNumber2;
                    }
                    errorMessage = "";
                }
                break;
        }
    }

    // Metodo per calcolare il risultato di un'operazione
    private double Calc(double number1, String operator, double number2) throws ArithmeticException {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                if (number2 == 0) {
                	errorMessage = "Errore divisione per 0";
                    
                }
                return number1 / number2;
            default:
                throw new ArithmeticException("Operatore non valido");
        }
    }

    // Metodo per verificare se una stringa è un numero
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
