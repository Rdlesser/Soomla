package com.soomla.robertlesser.soomla;

/**
 * Created by robertlesser on 28/09/2017.
 */

public class JavascriptCodeBuilder {

    private final String[] icons = {
            "https://avatars3.githubusercontent.com/u/2118838?v=4&s=200",
            "https://maxcdn.icons8.com/Share/icon/color/Gaming//bullbasaur1600.png",
            "https://lh3.googleusercontent.com/ez8pDFoxU2ZqDmyfeIjIba6dWisd8MY_6choHhZNpO0WwLhICu0v0s5eV2WHOhuhKw=w170",
            "https://lh3.googleusercontent.com/YGqr3CRLm45jMF8eM8eQxc1VSERDTyzkv1CIng0qjcenJZxqV5DBgH5xlRTawnqNPcOp=w300",
            "https://maxcdn.icons8.com/Share/icon/color/Users//donald_trump1600.png"
    };

    private final String[] links = {
            "http://www.hattrick.org/",
            "http://www.youtube.com/",
            "http://www.google.com",
            "http://www.something.com/"
    };

    public String buildCode(){
        String randomIcon = Utility.getRandom(icons);
        String randomLink = Utility.getRandom(links);
        String code = "var FunctionOne = function () {"
                + "  try{document.getElementsByClassName('icon')[0].src='"
                + randomIcon
                + "'; "
                + "document.getElementById('fbAdLink').href='"
                + randomLink
                + "'; }catch(e){}"
                + "}; FunctionOne();";
        return code;
    }
}
