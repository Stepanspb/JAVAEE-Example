
package web;

import java.util.ArrayList;

public class Attribute {

    ArrayList<Parameter> list;

    public Attribute() {
        list = new ArrayList<>();
    }

    String showAllParameters() {
        StringBuilder sb = new StringBuilder();
        sb.append("<ol>");
        for (Parameter p : list) {
            sb = sb.append(p.printParameter());
        }
        sb.append("</ol>");
        return sb.toString();
    }

    String showNameParameters(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ol>");
        for (Parameter p : list) {
            if (p.getName().startsWith(name)) {
                sb = sb.append(p.printParameter());
            }
        }
        sb.append("</ol>");
        return sb.toString();
    }

    String showIntParameters(String s, String s2) {
        int n1 = Integer.parseInt(s);
        int n2 = Integer.parseInt(s2);
        StringBuilder sb = new StringBuilder();
        sb.append("<ol>");
        for (Parameter p : list) {
            if ((p.getValue() >= n1 && p.getValue() <= n2) || (p.getValue() >= n2 && p.getValue() <= n1)) {
                sb = sb.append(p.printParameter());
            }
        }
        sb.append("</ol>");
        return sb.toString();

    }

    boolean addParameter(Parameter parameter) {
        for (int i = 0; i < list.size(); i++) {
            if (parameter.getName().equals(list.get(i).getName())) {
                list.set(i, parameter);
                return true;
            }
        }
        return list.add(parameter);
    }
}

