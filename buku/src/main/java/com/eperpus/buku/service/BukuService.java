package com.eperpus.buku.service;

import com.eperpus.buku.entity.Buku;
import com.eperpus.buku.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BukuService {

    private final BukuRepository bukuRepository;

    @Autowired
    public BukuService(BukuRepository bukuRepository) {
        this.bukuRepository = bukuRepository;
    }

    public List<Buku> getAllBuku() {
        return bukuRepository.findAll();
    }

    public Optional<Buku> getBukuById(Long id) {
        return bukuRepository.findById(id);
    }

    public Buku createBuku(Buku buku) {
        return bukuRepository.save(buku);
    }

    public Buku updateBuku(Long id, Buku bukuDetails) {
        Optional<Buku> optionalBuku = bukuRepository.findById(id);
        if (optionalBuku.isPresent()) {
            Buku existingBuku = optionalBuku.get();
            existingBuku.setJudul(bukuDetails.getJudul());
            existingBuku.setPenulis(bukuDetails.getPenulis());
            existingBuku.setTahunTerbit(bukuDetails.getTahunTerbit());
            existingBuku.setKategori(bukuDetails.getKategori());
            return bukuRepository.save(existingBuku);
        } else {
            // Handle not found, e.g., throw an exception
            return null; // Or throw new ResourceNotFoundException("Buku not found with id " + id);
        }
    }

    public void deleteBuku(Long id) {
        bukuRepository.deleteById(id);
    }
}