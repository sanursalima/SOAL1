package com.eperpus.buku.controller;

import com.eperpus.buku.entity.Buku;
import com.eperpus.buku.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buku")
public class BukuController {

    private final BukuService bukuService;

    @Autowired
    public BukuController(BukuService bukuService) {
        this.bukuService = bukuService;
    }

    // Endpoint untuk mengambil semua data buku
    @GetMapping
    public ResponseEntity<List<Buku>> getAllBuku() {
        List<Buku> bukuList = bukuService.getAllBuku();
        return new ResponseEntity<>(bukuList, HttpStatus.OK);
    }

    // Endpoint untuk mengambil data buku berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBukuById(@PathVariable Long id) {
        Optional<Buku> buku = bukuService.getBukuById(id);
        return buku.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint untuk menambah data buku
    @PostMapping
    public ResponseEntity<Buku> createBuku(@RequestBody Buku buku) {
        Buku createdBuku = bukuService.createBuku(buku);
        return new ResponseEntity<>(createdBuku, HttpStatus.CREATED);
    }

    // Endpoint untuk mengedit data buku
    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBuku(@PathVariable Long id, @RequestBody Buku buku) {
        Buku updatedBuku = bukuService.updateBuku(id, buku);
        if (updatedBuku != null) {
            return new ResponseEntity<>(updatedBuku, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint untuk menghapus data buku
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuku(@PathVariable Long id) {
        bukuService.deleteBuku(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}