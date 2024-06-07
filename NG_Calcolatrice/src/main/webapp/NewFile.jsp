<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Calcolatrice NG</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .calculator {
            background-color: #fff;
            border: 2px solid #ccc;
            border-radius: 10px;
            padding: 10px;
            box-shadow: 0px 0px 100px rgba(0, 0, 0, 0.1);
            width: 300px;
            max-width: 100%;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }

        .display {
            width: 100%;
            height: 50px;
            font-size: 24px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            text-align: right;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        .button-container {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 5px;
        }

        .button {
            height: 70px;
            font-size: 24px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f0f0f0;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #e0e0e0;
        }

        .button.equal {
            grid-column: span 2;
        }

        .button.clear {
            background-color: #ff6666;
            color: #fff;
        }

        .button.clear:hover {
            background-color: #ff5050;
        }
    </style> 
</head>
<body>
    <div class="calculator">
        <h2>Calcolatrice NG</h2>
        <form name="calculatorForm" action="Hello" method="post">
            <textarea class="display" name="currentInput" readonly>${currentInput}</textarea>
            <div class="button-container">
                <input class="button" type="submit" name="input" value="7">
                <input class="button" type="submit" name="input" value="8">
                <input class="button" type="submit" name="input" value="9">
                <input class="button" type="submit" name="input" value="/">
                <input class="button" type="submit" name="input" value="4">
                <input class="button" type="submit" name="input" value="5">
                <input class="button" type="submit" name="input" value="6">
                <input class="button" type="submit" name="input" value="*">
                <input class="button" type="submit" name="input" value="1">
                <input class="button" type="submit" name="input" value="2">
                <input class="button" type="submit" name="input" value="3">
                <input class="button" type="submit" name="input" value="-">
                <input class="button" type="submit" name="input" value="+">
                <input class="button" type="submit" name="input" value="0">
                <input class="button equal" type="submit" name="input" value="=">
                <input class="button clear" type="submit" name="input" value="C">
            </div>
        </form>
    </div>
</body>
</html>
