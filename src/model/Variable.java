package model;

public class Variable {
    private String name ;
    private Equation value ;
    private boolean isSetValue ;

    public Variable(String name,Equation value){
        if (name != null){
            char c = name.charAt(0);
            Database.getDatabase().getInconsistencyManager()[c - 'A'] = true ;
            for (char c1 : value.getPostfixEquation().toCharArray()){
                if (Character.isLetter(c1)){
                    Database.getDatabase().getCircularDependency()[c - 'A'][c1 - 'A'] = true ;
                }
            }
            setName(name);
        }
        setValue(value);
        setSetValue(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Equation getValue() {
        return value;
    }

    public void setValue(Equation value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return isSetValue;
    }

    public void setSetValue(boolean setValue) {
        isSetValue = setValue;
    }
}
