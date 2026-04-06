//package com.pao.laboratory05.audit;
//
//public record AuditEntry (String action, String target, String timestamp){
//
//    public String formattedTime(){
//        return java.time.LocalDateTime.now().toString();
//    }
//
//}
//
//
////public record AuditEntry(String action, String target, String timestamp) { }
////```
////        - `action` — what was done (ex: `"ADD"`, `"FIND_BY_DEPT"`)
////- `target` — the affected object (ex: employee name or department name)
////- `timestamp` — moment of the action; use `java.time.LocalDateTime.now().toString()`