(function () {
    'use strict';

    angular
        .module("gpualgoApp")
        .service('OverlockService', OverlockService);

    function OverlockService() {

        this.gpuArchitecture = [
            "NVIDIA",
            "AMD",
            "INTEL"
        ];

        this.gpuBrand = [
            "GENERIC NVIDIA",
            "GENERIC AMD",
            "Asus",
            "Gigabyte",
            "EVGA",
            "MSI",
            "Zotac",
            "EVGA",
            "PNY",
            "Sapphire",
            "Intel"
        ];

        this.gpuModel = {
            "GENERIC NVIDIA": [
                "Titan V",
                "Titan Xp",
                "Titan X (Pascal, 2016)",
                "GTX 1080 Ti",
                "GTX 1080",
                "GTX 1070 Ti",
                "GTX 1070",
                "GTX 1070 Founders Edition",
                "GTX 1070 Ti - Founders Edition",
                "GTX Titan X (Maxwell, 2015)",
                "GTX 980 Ti",
                "GTX 1060 6GB",
                "GTX 980",
                "GTX 1060 3GB",
                "GTX 970",
                "GTX 780 Ti",
                "GTX 780",
                "GTX 770",
                "GTX 590",
                "GTX 1050 Ti",
                "GTX 1050",
                "GTX 960",
                "GTX 670",
                "GTX 580",
                "GTX 950",
                "GTX 760",
                "GTX 660 Ti",
                "GTX 660",
                "GTX 570",
                "GTX 480",
                "GTX 750 Ti",
                "GTX 560 Ti",
                "GTX 470",
                "GTX 750",
                "GTX 650 Ti",
                "GT 1030",
                "GTX 560",
                "GTX 460",
                "GT 740",
                "GT 650",
                "GTX 550 Ti"
            ],
            "GENERIC AMD": [
                    "RX Vega 64",
                    "RX Vega 56",
                    "R9 Fury X",
                    "RX 580",
                    "RX 480 8GB",
                    "RX 480 4GB",
                    "R9 Fury",
                    "R9 Fury Nano",
                    "R9 390X",
                    "RX 570",
                    "R9 390",
                    "R9 290X",
                    "RX 470",
                    "R9 380X",
                    "R9 290",
                    "HD 7970",
                    "RX 560 4GB",
                    "R9 380",
                    "R9 280X",
                    "R9 280",
                    "HD 7950",
                    "HD 5970",
                    "RX 560 2GB",
                    "R7 370",
                    "R9 270X",
                    "HD 7870",
                    "RX 460",
                    "R9 270",
                    "HD 7850",
                    "HD 6970",
                    "R7 260X",
                    "HD 6950",
                    "RX 550",
                    "HD 5870",
                    "HD 7790",
                    "HD 6870",
                    "HD 5850",
                    "Vega 11",
                    "R7 360",
                    "R7 260",
                    "HD 7770",
                    "HD 6850",
                    "R7 250X",
                    "HD 7750",
                    "HD 6770",
                    "Vega 8",
                    "R7 250",
                    "HD 5770"
                ],
            "Asus": [
                    "Titan V",
                    "Titan Xp",
                    "Titan X (Pascal, 2016)",
                    "GTX 1080 Ti",
                    "GTX 1080",
                    "GTX 1070 Ti",
                    "GTX 1070",
                    "GTX 1070 Founders Edition",
                    "GTX 1070 Ti - Founders Edition",
                    "GTX Titan X (Maxwell, 2015)",
                    "GTX 980 Ti",
                    "GTX 1060 6GB",
                    "GTX 980",
                    "GTX 1060 3GB",
                    "GTX 970",
                    "GTX 780 Ti",
                    "GTX 780",
                    "GTX 770",
                    "GTX 590",
                    "GTX 1050 Ti",
                    "GTX 1050",
                    "GTX 960",
                    "GTX 670",
                    "GTX 580",
                    "GTX 950",
                    "GTX 760",
                    "GTX 660 Ti",
                    "GTX 660",
                    "GTX 570",
                    "GTX 480",
                    "GTX 750 Ti",
                    "GTX 560 Ti",
                    "GTX 470",
                    "GTX 750",
                    "GTX 650 Ti",
                    "GT 1030",
                    "GTX 560",
                    "GTX 460",
                    "GT 740",
                    "GT 650",
                    "GTX 550 Ti"
                ],
            "Gigabyte": [
                    "Titan V",
                    "Titan Xp",
                    "Titan X (Pascal, 2016)",
                    "GTX 1080 Ti",
                    "GTX 1080",
                    "GTX 1070 Ti",
                    "GTX 1070",
                    "GTX 1070 Founders Edition",
                    "GTX 1070 Ti - Founders Edition",
                    "GTX Titan X (Maxwell, 2015)",
                    "GTX 980 Ti",
                    "GTX 1060 6GB",
                    "GTX 980",
                    "GTX 1060 3GB",
                    "GTX 970",
                    "GTX 780 Ti",
                    "GTX 780",
                    "GTX 770",
                    "GTX 590",
                    "GTX 1050 Ti",
                    "GTX 1050",
                    "GTX 960",
                    "GTX 670",
                    "GTX 580",
                    "GTX 950",
                    "GTX 760",
                    "GTX 660 Ti",
                    "GTX 660",
                    "GTX 570",
                    "GTX 480",
                    "GTX 750 Ti",
                    "GTX 560 Ti",
                    "GTX 470",
                    "GTX 750",
                    "GTX 650 Ti",
                    "GT 1030",
                    "GTX 560",
                    "GTX 460",
                    "GT 740",
                    "GT 650",
                    "GTX 550 Ti"
                ],
            "EVGA": [
                    "Titan V",
                    "Titan Xp",
                    "Titan X (Pascal, 2016)",
                    "GTX 1080 Ti",
                    "GTX 1080",
                    "GTX 1070 Ti",
                    "GTX 1070",
                    "GTX 1070 Founders Edition",
                    "GTX 1070 Ti - Founders Edition",
                    "GTX Titan X (Maxwell, 2015)",
                    "GTX 980 Ti",
                    "GTX 1060 6GB",
                    "GTX 980",
                    "GTX 1060 3GB",
                    "GTX 970",
                    "GTX 780 Ti",
                    "GTX 780",
                    "GTX 770",
                    "GTX 590",
                    "GTX 1050 Ti",
                    "GTX 1050",
                    "GTX 960",
                    "GTX 670",
                    "GTX 580",
                    "GTX 950",
                    "GTX 760",
                    "GTX 660 Ti",
                    "GTX 660",
                    "GTX 570",
                    "GTX 480",
                    "GTX 750 Ti",
                    "GTX 560 Ti",
                    "GTX 470",
                    "GTX 750",
                    "GTX 650 Ti",
                    "GT 1030",
                    "GTX 560",
                    "GTX 460",
                    "GT 740",
                    "GT 650",
                    "GTX 550 Ti"
                ],
            "MSI": [
                    "Titan V",
                    "Titan Xp",
                    "Titan X (Pascal, 2016)",
                    "GTX 1080 Ti",
                    "GTX 1080",
                    "GTX 1070 Ti",
                    "GTX 1070",
                    "GTX 1070 Founders Edition",
                    "GTX 1070 Ti - Founders Edition",
                    "GTX Titan X (Maxwell, 2015)",
                    "GTX 980 Ti",
                    "GTX 1060 6GB",
                    "GTX 980",
                    "GTX 1060 3GB",
                    "GTX 970",
                    "GTX 780 Ti",
                    "GTX 780",
                    "GTX 770",
                    "GTX 590",
                    "GTX 1050 Ti",
                    "GTX 1050",
                    "GTX 960",
                    "GTX 670",
                    "GTX 580",
                    "GTX 950",
                    "GTX 760",
                    "GTX 660 Ti",
                    "GTX 660",
                    "GTX 570",
                    "GTX 480",
                    "GTX 750 Ti",
                    "GTX 560 Ti",
                    "GTX 470",
                    "GTX 750",
                    "GTX 650 Ti",
                    "GT 1030",
                    "GTX 560",
                    "GTX 460",
                    "GT 740",
                    "GT 650",
                    "GTX 550 Ti"
                ],
            "Zotac": [
                    "Titan V",
                    "Titan Xp",
                    "Titan X (Pascal, 2016)",
                    "GTX 1080 Ti",
                    "GTX 1080",
                    "GTX 1070 Ti",
                    "GTX 1070",
                    "GTX 1070 Founders Edition",
                    "GTX 1070 Ti - Founders Edition",
                    "GTX Titan X (Maxwell, 2015)",
                    "GTX 980 Ti",
                    "GTX 1060 6GB",
                    "GTX 980",
                    "GTX 1060 3GB",
                    "GTX 970",
                    "GTX 780 Ti",
                    "GTX 780",
                    "GTX 770",
                    "GTX 590",
                    "GTX 1050 Ti",
                    "GTX 1050",
                    "GTX 960",
                    "GTX 670",
                    "GTX 580",
                    "GTX 950",
                    "GTX 760",
                    "GTX 660 Ti",
                    "GTX 660",
                    "GTX 570",
                    "GTX 480",
                    "GTX 750 Ti",
                    "GTX 560 Ti",
                    "GTX 470",
                    "GTX 750",
                    "GTX 650 Ti",
                    "GT 1030",
                    "GTX 560",
                    "GTX 460",
                    "GT 740",
                    "GT 650",
                    "GTX 550 Ti"
                ],
            "PNY": [
                    "Titan V",
                    "Titan Xp",
                    "Titan X (Pascal, 2016)",
                    "GTX 1080 Ti",
                    "GTX 1080",
                    "GTX 1070 Ti",
                    "GTX 1070",
                    "GTX 1070 Founders Edition",
                    "GTX 1070 Ti - Founders Edition",
                    "GTX Titan X (Maxwell, 2015)",
                    "GTX 980 Ti",
                    "GTX 1060 6GB",
                    "GTX 980",
                    "GTX 1060 3GB",
                    "GTX 970",
                    "GTX 780 Ti",
                    "GTX 780",
                    "GTX 770",
                    "GTX 590",
                    "GTX 1050 Ti",
                    "GTX 1050",
                    "GTX 960",
                    "GTX 670",
                    "GTX 580",
                    "GTX 950",
                    "GTX 760",
                    "GTX 660 Ti",
                    "GTX 660",
                    "GTX 570",
                    "GTX 480",
                    "GTX 750 Ti",
                    "GTX 560 Ti",
                    "GTX 470",
                    "GTX 750",
                    "GTX 650 Ti",
                    "GT 1030",
                    "GTX 560",
                    "GTX 460",
                    "GT 740",
                    "GT 650",
                    "GTX 550 Ti"
                ],
            "Sapphire": [
                    "Titan V",
                    "Titan Xp",
                    "Titan X (Pascal, 2016)",
                    "GTX 1080 Ti",
                    "GTX 1080",
                    "GTX 1070 Ti",
                    "GTX 1070",
                    "GTX 1070 Founders Edition",
                    "GTX 1070 Ti - Founders Edition",
                    "GTX Titan X (Maxwell, 2015)",
                    "GTX 980 Ti",
                    "GTX 1060 6GB",
                    "GTX 980",
                    "GTX 1060 3GB",
                    "GTX 970",
                    "GTX 780 Ti",
                    "GTX 780",
                    "GTX 770",
                    "GTX 590",
                    "GTX 1050 Ti",
                    "GTX 1050",
                    "GTX 960",
                    "GTX 670",
                    "GTX 580",
                    "GTX 950",
                    "GTX 760",
                    "GTX 660 Ti",
                    "GTX 660",
                    "GTX 570",
                    "GTX 480",
                    "GTX 750 Ti",
                    "GTX 560 Ti",
                    "GTX 470",
                    "GTX 750",
                    "GTX 650 Ti",
                    "GT 1030",
                    "GTX 560",
                    "GTX 460",
                    "GT 740",
                    "GT 650",
                    "GTX 550 Ti"
                ],
            "Intel": [
                    "HD2500",
                    "HD4x00",
                    "HD5300 - 6000",
                    "HD400",
                    "HD510 - P530",
                    "HD610 - 630",
                    "HD4000",
                    "HD5x00",
                    "Iris 6100 - P6300",
                    "HD405",
                    "Iris 540 - P580",
                    "Iris Plus 640 - 650"
                ]};

        this.algorithm = [
            "",
            "SHA-256",
            "Dagger-Hashimoto",
            "SHA-256",
            "ECDSA",
            "SHA256",
            "CryptoNight",
            "blake2b",
            "Scrypt",
            "Dagger-Hashimoto",
            "Scrypt",
            "blockchain",
            "Scrypt",
            "Smart contract",
            "X11",
            "CryptoNight",
            "Scrypt",
            "LBRY",
            "Smartchain",
            "Blake256",
            "Scrypt",
            "BOINC",
            "Lyra2RE",
            "CryptoNight",
            "Scrypt",
            "SHA-256",
            "SHA-256",
            "NXT",
            "Dagger-Hashimoto",
            "X11",
            "Stanford Folding",
            "X13",
            "Scrypt",
            "Scrypt",
            "Blake256"
        ];
    }

})();
