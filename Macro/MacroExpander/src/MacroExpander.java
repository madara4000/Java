import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MacroExpander {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("arquivo.asm"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("saida.asm"))) {
            String line;
            int indentationLevel = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("MACRO1")) {
                    String[] params = line.substring(line.indexOf("MACRO1") + 6).split(",");
                    String expanded = expandMacro1(params, indentationLevel);
                    bw.write(expanded);
                } else if (line.trim().startsWith("MACRO2")) {
                    String[] params = line.substring(line.indexOf("MACRO2") + 6).split(",");
                    String expanded = expandMacro2(params, indentationLevel);
                    bw.write(expanded);

                } 
            else if (line.trim().startsWith("MACRO3")) {
                String[] params = line.substring(line.indexOf("MACRO3") + 6).split(",");
                String expanded = expandMacro3(params[0].trim(), indentationLevel);
                bw.write(expanded);
            } else if (line.trim().startsWith("MACRO4")) {
              String[] params = line.substring(line.indexOf("MACRO4") + 6).split(",");
                  String expanded = expandMacro4(params[0].trim(), indentationLevel);
                   bw.write(expanded);
            }
                else {
                   bw.write(line);
                } 
                bw.newLine();
                indentationLevel = countIndentation(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler/gravar o arquivo: " + e.getMessage());
        }
    }

    private static int countIndentation(String line) {
        int count = 0;
        while (count < line.length() && (line.charAt(count) == ' ' || line.charAt(count) == '\t')) {
            count++;
        }
        return count;
    }


private static String expandMacro1(String[] params, int indentationLevel) {
    String tabulation = generateTabulation(indentationLevel);
    String result = tabulation + "ADD R0, " + params[0].trim() + "\n";
    result += tabulation + "ADD R0, " + params[1].trim() + "\n";
    result += tabulation + "DIV R0, 2\n";
    return result;
}

private static String expandMacro2(String[] params, int indentationLevel) {
    String tabulation = generateTabulation(indentationLevel);
    String result = tabulation + "ADD R0, " + params[0].trim() + "\n";
    result += tabulation + "ADD R0, " + params[1].trim() + "\n";
    result += tabulation + "ADD R0, " + params[2].trim() + "\n";
    result += tabulation + "DIV R0, 3\n";
    return result;
}

private static String expandMacro3(String register, int indentationLevel) {
    String tabulation = generateTabulation(indentationLevel);
    String result = tabulation + "MOV R0, " + register + "\n";
    result += tabulation + "MUL R0, R0\n";
    result += tabulation + "MUL R0, R0\n";
    return result;
}
private static String expandMacro4(String register, int indentationLevel) {
    String tabulation = generateTabulation(indentationLevel);
    String result = tabulation + "MOV R0, " + register + "\n";
    result += tabulation + "MUL R0, R0\n";
    result += tabulation + "MUL R0, R0\n";
    result += tabulation + "MUL R0, R0\n";
    return result;

}

  

private static String generateTabulation(int level) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < level; i++) {
        sb.append(' ');
    }
    return sb.toString();

}
}

/*+{
    // Use IntelliSense to learn about possible attributes.
  // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [
        {
            "type": "(Windows) Launch",
            "request": "launch",
            "name": "Debug",
            "program": "${workspaceFolder}/<executable file>",
            "args": [],
            "cwd": "${workspaceFolder}"
        }
    ]
}    // Hover to view descriptions of existing attributes.
   */