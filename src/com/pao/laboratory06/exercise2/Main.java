package com.pao.laboratory06.exercise2;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        java.util.Locale.setDefault(java.util.Locale.US);
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Colaborator> colaboratori = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String tip = in.next();
            Colaborator c = switch (tip) {
                case "CIM" -> {
                    CIMColaborator obj = new CIMColaborator();
                    obj.read(in);
                    yield obj;
                }
                case "PFA" -> {
                    PFAColaborator obj = new PFAColaborator();
                    obj.read(in);
                    yield obj;
                }
                case "SRL" -> {
                    SRLColaborator obj = new SRLColaborator();
                    obj.read(in);
                    yield obj;
                }
                default -> throw new IllegalArgumentException("Unknown type: " + tip);
            };
            colaboratori.add(c);
        }
        // Sort and display by type, each descending by annual net income
        for (ColaboratorType tipColab : ColaboratorType.values()) {
            colaboratori.stream()
                    .filter(c -> c.getType() == tipColab)
                    .sorted((a, b) -> Double.compare(b.calculateAnnualNetIncome(), a.calculateAnnualNetIncome()))
                    .forEach(Colaborator::display);
        }
        // Collaborator with maximum net income
        Colaborator max = colaboratori.stream().max(Comparator.comparingDouble(Colaborator::calculateAnnualNetIncome)).orElse(null);
        System.out.printf("\nColaborator cu venit net maxim: ");
        if (max != null) max.display();
        // Legal entity collaborators (SRL)
        System.out.println("\nColaboratori persoane juridice:");
        colaboratori.stream()
                .filter(c -> c instanceof LegalEntity)
                .sorted((a, b) -> Double.compare(b.calculateAnnualNetIncome(), a.calculateAnnualNetIncome()))
                .forEach(Colaborator::display);
        // Sums and counts by collaborator type
        System.out.println("\nSume și număr colaboratori pe tip:");
        Map<ColaboratorType, Double> sum = new EnumMap<>(ColaboratorType.class);
        Map<ColaboratorType, Integer> count = new EnumMap<>(ColaboratorType.class);
        var typesOfCollaborators = new HashSet<ColaboratorType>();
        for (Colaborator c : colaboratori) {
            typesOfCollaborators.add(c.getType());
        }
        for (ColaboratorType t : typesOfCollaborators) {
            sum.put(t, 0.0);
            count.put(t, 0);
        }
        for (Colaborator c : colaboratori) {
            ColaboratorType t = c.getType();
            sum.put(t, sum.get(t) + c.calculateAnnualNetIncome());
            count.put(t, count.get(t) + 1);
        }
        for (ColaboratorType t : ColaboratorType.values()) {
            System.out.printf("%s: suma = %.2f lei, număr = %d\n", t, sum.get(t), count.get(t));
        }
    }
}