package transpiler.medium2target;

import transpiler.mediumcodemodel.EOSourceFile;
import transpiler.mediumcodemodel.EOTargetFile;

import java.util.ArrayList;

public class Medium2TargetTranspiler {
    public static void transpile(EOSourceFile file) {
        ArrayList<EOTargetFile> result = file.transpile(null);
        return;
    }
}
