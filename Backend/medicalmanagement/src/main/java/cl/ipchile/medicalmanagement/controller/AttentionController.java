package cl.ipchile.medicalmanagement.controller;

import cl.ipchile.medicalmanagement.model.*;
import cl.ipchile.medicalmanagement.service.AttentionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attentions")
public class AttentionController {

    private final AttentionService attentionService;

    @Autowired
    public AttentionController(AttentionService attentionService) {
        this.attentionService = attentionService;
    }

    @GetMapping()
    public List<AttentionDTO> getAllAttentions() {
        return attentionService.getAllAttentions();
    }

    @GetMapping("/{id}")
    public AttentionDTO getAttention(@PathVariable Long id) {
        return attentionService.getAttention(id);
    }

    @PostMapping()
    public AttentionDTO createAttention(@Valid @RequestBody AttentionDTO attentionDTO) {
        return attentionService.createAttention(attentionDTO);
    }

    @PutMapping("/{id}")
    public AttentionDTO updateAttention(@PathVariable Long id, @Valid @RequestBody AttentionDTO attentionDTO) {
        return attentionService.updateAttention(id, attentionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAttention(@PathVariable Long id) {
        attentionService.deleteAttention(id);
    }
}