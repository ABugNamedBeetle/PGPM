package priv.pgpm.cfi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import priv.pgpm.cfi.models.requests.InstructionRequest;
import priv.pgpm.cfi.models.responses.InstructionResponse;
import priv.pgpm.cfi.services.InstructionProcessingService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/${cfi.api.version}")

public class ClearanceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClearanceController.class);
    private InstructionProcessingService ips;

    public ClearanceController(InstructionProcessingService ips) {
        this.ips = ips;
    }

    @PostMapping("accept-instructions")
    public ResponseEntity<?> acceptInstructions(@RequestBody @Validated InstructionRequest request, BindingResult bindingResult) {
       
        if (bindingResult.hasErrors()) {
           Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            Map<String, Object> respMap = Map.of("message", "Instruction posting failed with validations", "errors", errors);
            return ResponseEntity.badRequest().body(respMap);
        }
        InstructionResponse.Accepted accepted = ips.acceptInstructionRequest(request.getRequestId(), request.extractMessages());
        return new ResponseEntity<>(accepted, HttpStatus.ACCEPTED);
    }

    // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    // @ExceptionHandler(value = MethodArgumentNotValidException.class)
    // public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
    //     Map<String, String> errors = new HashMap<>();
    //     System.out.println("Here");
    //     ex.getAllErrors().forEach((error) -> {
    //         String fieldName = ((FieldError) error).getField();
    //         String errorMessage = error.getDefaultMessage();
    //         errors.put(fieldName, errorMessage);
    //     });
    //     return errors;
    // }

}
