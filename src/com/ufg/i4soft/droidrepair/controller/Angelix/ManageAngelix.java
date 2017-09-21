package com.ufg.i4soft.droidrepair.controller.Angelix;

import com.ufg.i4soft.droidrepair.contract.RepairTool;
import com.ufg.i4soft.droidrepair.controller.DiffAndroidStudio;
import com.ufg.i4soft.droidrepair.controller.*;
import com.ufg.i4soft.droidrepair.model.FilterData;
import com.ufg.i4soft.droidrepair.model.ProjectData;
import com.ufg.i4soft.droidrepair.view.windows.MainWindows;

import java.util.ArrayList;

public class ManageAngelix implements RepairTool{

    public void startRepairTool(String path){

        ArrayList<String> args = collectParameters(path);

        verifyInputs(args);
    }

    public ArrayList<String> collectParameters(String path){

        ArrayList<String> parameters = new ArrayList<>();
        String[] subPathes;

        FilterData filterData = new FilterData();

        subPathes = filterData.splitPath(path);

        if (path != null){

            setPathofPatch(subPathes[0]); //Caminho onde sera gerado o patch

            parameters.add(subPathes[0]); // Diretório: sem o arquivo
            parameters.add(subPathes[1]); // Arquivo a ser executado

            String outros_paramentros = MainWindows.viewInput();
            parameters.add(outros_paramentros);

        } else {
            parameters.add(null);
        }

        return parameters;
    }

    public void verifyInputs(ArrayList<String> args) {

        boolean parametrosNaoVazios = true;

        for (String arg : args) {
            if (arg == null) {
                parametrosNaoVazios = false;
            }
        }

        if (parametrosNaoVazios) {
            executeRepairTool(args);
        }
    }

    public void executeRepairTool(ArrayList<String> args) {

        ExecuteShell shell = new ExecuteShell();

        String command = "cd ~/angelix;" +
                ". activate;" +
                "cd " + args.get(0) + ";" + //Mudar para diretório onde será gerado o patch
                "~/angelix/src/tools/angelix " + //executar o angelix
                args.get(0) + " " + args.get(1) + " " + args.get(2); //parametros para o angelix

        FileManipulation fileManipulation = new FileManipulation();
        fileManipulation.deleteAllPatches();

        String statusline = shell.executeCommand(command);

        if (statusline.equals("sucess")){

            fileManipulation.searchFilePatch();
            fileManipulation.deleteOldLines();

            DiffAndroidStudio diff = new DiffAndroidStudio();
            diff.showDiff();
        }

    }

    private void setPathofPatch(String path){

        ProjectData.setPath_of_patch(path);
    }

}
