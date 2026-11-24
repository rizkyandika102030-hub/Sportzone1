
const harga = {
    "Futsal A": 100000,
    "Futsal B": 120000,
    "Badminton": 50000
};

// Memilih lapangan dari tombol kartu
function pilihLapangan(nama) {
    document.getElementById("lapangan").value = nama;

    document.getElementById("pesan")
        .scrollIntoView({ behavior: "smooth" });

    hitungTotal();
}

// Menghitung biaya otomatis
function hitungTotal() {
    const lapangan = document.getElementById("lapangan").value;
    const durasi = Number(document.getElementById("durasi").value);

    const biaya = (harga[lapangan] || 0) * durasi;

    document.getElementById("total").innerText =
        "Total: Rp" + biaya.toLocaleString("id-ID");
}

document.getElementById("lapangan")
    .addEventListener("change", hitungTotal);

document.getElementById("durasi")
    .addEventListener("input", hitungTotal);

// Konfirmasi pemesanan
document.getElementById("formPesan")
    .addEventListener("submit", function(e) {
        e.preventDefault();

        const nama = document.getElementById("nama").value;
        const nomor = document.getElementById("nomor").value;
        const tanggal = document.getElementById("tanggal").value;
        const lapangan = document.getElementById("lapangan").value;
        const durasi = Number(document.getElementById("durasi").value);

        if (!harga[lapangan] || durasi < 1 || durasi > 12) {
            alert("Periksa pilihan lapangan dan durasi.");
            return;
        }

        const total = harga[lapangan] * durasi;

        const konfirmasi = confirm(
            "KONFIRMASI PESANAN\n\n" +
            "Nama: " + nama + "\n" +
            "Nomor WA: " + nomor + "\n" +
            "Tanggal: " + tanggal + "\n" +
            "Lapangan: " + lapangan + "\n" +
            "Durasi: " + durasi + " jam\n" +
            "Total: Rp" + total.toLocaleString("id-ID") +
            "\n\nKirim pesanan melalui WhatsApp?"
        );

        if (konfirmasi) {
            const pesan =
                "Halo Lapangan Sehat, saya ingin memesan lapangan.\n\n" +
                "Nama: " + nama + "\n" +
                "Nomor WA: " + nomor + "\n" +
                "Tanggal: " + tanggal + "\n" +
                "Lapangan: " + lapangan + "\n" +
                "Durasi: " + durasi + " jam\n" +
                "Total: Rp" + total.toLocaleString("id-ID");

            // Ganti dengan nomor WhatsApp pengelola
            const nomorAdmin = "6281234567890";

            window.open(
                "https://wa.me/" + nomorAdmin +
                "?text=" + encodeURIComponent(pesan),
                "_blank"
            );
        }
    });
