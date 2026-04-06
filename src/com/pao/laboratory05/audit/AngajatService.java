//package com.pao.laboratory05.audit;
//
//import com.pao.laboratory05.angajati.AngajatService;
//
//public class AngajatService {
//
//    private AuditEntry[] auditLog = new AuditEntry[0];
//
//    // private constructor
//    private AngajatService(){}
//
//    // getInstance()
//    private static class Holder {
//        private static final AngajatService instance = new AngajatService();
//    }
//
//    public static AngajatService getInstance(){
//        return Holder.instance;
//    }
//
//    private void logAction(String action, String target) {
//        AuditEntry[] newAuditLog = new AuditEntry[auditLog.length + 1];
//        System.arraycopy(this.auditLog, 0, newAuditLog, 0, auditLog.length);
//        newAuditLog[auditLog.length] = AuditEntry;
//        this.auditLog = newAuditLog;
//        System.out.println("sth")
//
//    }
//
//
//}
//
//
////#### `AngajatService.java` — Singleton with audit
////Same as in Ex3, plus:
////        - Additional field: `private AuditEntry[] auditLog` (initialized `new AuditEntry[0]`)
////        - Private method `logAction(String action, String target)` — create an `AuditEntry`
////with `LocalDateTime.now().toString()` and add it to `auditLog` (resize pattern)
////        - `addAngajat` → call `logAction("ADD", angajat.getNume())` after adding
////- `findByDepartament` → call `logAction("FIND_BY_DEPT", numeDept)` at the beginning
////- `void printAuditLog()` — traverse and display all entries