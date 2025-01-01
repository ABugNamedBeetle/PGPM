package priv.pgpm.cfi.models.responses;

import lombok.Data;

public final class InstructionResponse {
    
    @Data
    public static class Accepted{
        private String message;
        public Accepted(int count, String requestId){
            this.message = String.format("Successfully accepted %s instructions with Request Id %s", count, requestId);
        }
    }
}
