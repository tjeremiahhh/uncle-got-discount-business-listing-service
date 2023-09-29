package com.businesslisting.common.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.extractor.MainExtractorFactory;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.macros.VBAMacroReader;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.tika.Tika;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

public class FileUtil {
    public static final List<String> DOC = Arrays.asList("application/msword");
    public static final List<String> DOCX = Arrays.asList("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
    public static final List<String> XLS = Arrays.asList("application/vnd.ms-excel");
    public static final List<String> XLSX = Arrays.asList("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    public static final List<String> MSG = Arrays.asList("application/vnd.ms-outlook");
    public static final List<String> CSV = Arrays.asList("text/csv");
    public static final List<String> PDF = Arrays.asList("application/pdf");

    private static final Long fileSizeLimit = Long.valueOf(10 * 1024 * 1024);

    public static void isIllegalFileUpload(MultipartFile attachmentFile, List<List<String>> allowedMediaTypes) {
        try {
            if (attachmentFile != null) {
                String mediaType = getMediaType(attachmentFile);

                isValidFileName(attachmentFile);
                isValidFileSize(attachmentFile);
                hasAllowedExtension(mediaType, allowedMediaTypes);
                hasMacroOrEmbedded(attachmentFile, mediaType);

            }
        } catch (IOException | OpenXML4JException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unabled to upload file. Please contact helpdesk.");
        }
    }

    public static void isIllegalFileDownload(File attachmentFile, List<List<String>> allowedMediaTypes) {
        try {
            if (attachmentFile != null) {
                String mediaType = getMediaType(attachmentFile);

                isValidFileName(attachmentFile);
                isValidFileSize(attachmentFile);
                hasAllowedExtension(mediaType, allowedMediaTypes);
                hasMacroOrEmbedded(attachmentFile, mediaType);

            }
        } catch (IOException | OpenXML4JException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unabled to download file. Please contact helpdesk.");
        }
    }

    private static String getMediaType(MultipartFile attachmentFile) throws IOException {
        try (InputStream inputStream = attachmentFile.getInputStream()) {
            Tika tika = new Tika();
            return tika.detect(inputStream, attachmentFile.getOriginalFilename());
        }
    }

    private static String getMediaType(File attachmentFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(attachmentFile)) {
            Tika tika = new Tika();
            return tika.detect(inputStream, attachmentFile.getName());
        }
    }

    private static void isValidFileSize(MultipartFile attachmentFile) throws IOException {
        if (attachmentFile.getSize() > fileSizeLimit) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is larger than 10MB.");
        }
    }

    private static void isValidFileSize(File attachmentFile) throws IOException {
        if (attachmentFile.length() > fileSizeLimit) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is larger than 10MB.");
        }
    }

    private static void isValidFileName(MultipartFile attachmentFile) {
        String fileName = attachmentFile.getOriginalFilename();

        if (fileName == null || !fileName.matches("^[a-zA-Z0-9-_ ]{1,200}\\.[a-zA-Z0-9]{1,10}$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file name.");
        }
    }

    private static void isValidFileName(File attachmentFile) {
        String fileName = attachmentFile.getName();

        if (fileName == null || !fileName.matches("^[a-zA-Z0-9-_ ]{1,200}\\.[a-zA-Z0-9]{1,10}$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file name.");
        }
    }

    private static void hasAllowedExtension(String mediaType, List<List<String>> allowedMediaTypes) throws IOException {
        for (List<String> allowedMediaType : allowedMediaTypes) {
            if (allowedMediaType.contains(mediaType)) {
                return;
            }
        }
        
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file type.");
    }

    private static void hasMacroOrEmbedded(MultipartFile attachmentFile, String mediaType) throws IOException, OpenXML4JException {
        if (DOC.contains(mediaType) || DOCX.contains(mediaType) || XLS.contains(mediaType) || XLSX.contains(mediaType)) {
            hasMacro(attachmentFile);
        }

        if (DOC.contains(mediaType)) {
            hasDOCEmbedded(attachmentFile);
        } else if (DOCX.contains(mediaType)) {
            hasDOCXEmbedded(attachmentFile);
        } else if (XLS.contains(mediaType)) {
            hasXLSEmbedded(attachmentFile);
        } else if (XLSX.contains(mediaType)) {
            hasXLSXEmbedded(attachmentFile);
        }
    }

    private static void hasMacroOrEmbedded(File attachmentFile, String mediaType) throws IOException, OpenXML4JException {
        if (DOC.contains(mediaType) || DOCX.contains(mediaType) || XLS.contains(mediaType) || XLSX.contains(mediaType)) {
            hasMacro(attachmentFile);
        }

        if (DOC.contains(mediaType)) {
            hasDOCEmbedded(attachmentFile);
        } else if (DOCX.contains(mediaType)) {
            hasDOCXEmbedded(attachmentFile);
        } else if (XLS.contains(mediaType)) {
            hasXLSEmbedded(attachmentFile);
        } else if (XLSX.contains(mediaType)) {
            hasXLSXEmbedded(attachmentFile);
        }
    }

    private static void hasMacro(MultipartFile attachmentFile) throws IOException {
        try (InputStream inputStream = attachmentFile.getInputStream(); VBAMacroReader vbaMacroReader = new VBAMacroReader(inputStream);) {
            if (vbaMacroReader.readMacros().size() > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : Illegal Macro");
            }
        } catch (IllegalArgumentException ex) {
            if (!ex.getLocalizedMessage().equals("No VBA project found")) {
                throw ex;
            }
        }
    }

    private static void hasMacro(File attachmentFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(attachmentFile); VBAMacroReader vbaMacroReader = new VBAMacroReader(inputStream);) {
            if (vbaMacroReader.readMacros().size() > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : Illegal Macro");
            }
        } catch (IllegalArgumentException ex) {
            if (!ex.getLocalizedMessage().equals("No VBA project found")) {
                throw ex;
            }
        }
    }

    private static void hasDOCEmbedded(MultipartFile attachmentFile) throws IOException {
        try (InputStream inputStream = attachmentFile.getInputStream(); HWPFDocument document = new HWPFDocument(inputStream);) {
            ExtractorFactory.removeProvider(MainExtractorFactory.class);
            try {
                POITextExtractor[] embeddedExtractors = ExtractorFactory.getEmbeddedDocsTextExtractors(new WordExtractor(document));
                if (embeddedExtractors.length > 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : DOC Embedded");
                }
            } catch (IOException ioEx) {
                // if IOException, the document contain non office embedded object.
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unabled to download file. Please contact helpdesk.");
            }
        }
    }

    private static void hasDOCEmbedded(File attachmentFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(attachmentFile); HWPFDocument document = new HWPFDocument(inputStream);) {
            ExtractorFactory.removeProvider(MainExtractorFactory.class);
            try {
                POITextExtractor[] embeddedExtractors = ExtractorFactory.getEmbeddedDocsTextExtractors(new WordExtractor(document));
                if (embeddedExtractors.length > 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : DOC Embedded");
                }
            } catch (IOException ioEx) {
                // if IOException, the document contain non office embedded object.
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unabled to download file. Please contact helpdesk.");
            }
        }
    }

    private static void hasDOCXEmbedded(MultipartFile attachmentFile) throws OpenXML4JException, IOException {
        try (InputStream inputStream = attachmentFile.getInputStream(); XWPFDocument document = new XWPFDocument(inputStream)) {
            List<PackagePart> embeddedObjects = document.getAllEmbeddedParts();
            if (!embeddedObjects.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : DOCX Embedded");
            }
        }
    }

    private static void hasDOCXEmbedded(File attachmentFile) throws OpenXML4JException, IOException {
        try (InputStream inputStream = new FileInputStream(attachmentFile); XWPFDocument document = new XWPFDocument(inputStream)) {
            List<PackagePart> embeddedObjects = document.getAllEmbeddedParts();
            if (!embeddedObjects.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : DOCX Embedded");
            }
        }
    }

    private static void hasXLSEmbedded(MultipartFile attachmentFile) throws IOException {
        try (InputStream inputStream = attachmentFile.getInputStream(); HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {
            ExtractorFactory.removeProvider(MainExtractorFactory.class);
            try {
                POITextExtractor[] embeddedExtractors = ExtractorFactory.getEmbeddedDocsTextExtractors(new ExcelExtractor(workbook));
                if (embeddedExtractors.length > 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : XLS Embedded");
                }
            } catch (IOException ioEx) {
                // if IOException, the document contain non office embedded object.
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unabled to download file. Please contact helpdesk.");
            }
        }
    }

    private static void hasXLSEmbedded(File attachmentFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(attachmentFile); HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {
            ExtractorFactory.removeProvider(MainExtractorFactory.class);
            try {
                POITextExtractor[] embeddedExtractors = ExtractorFactory.getEmbeddedDocsTextExtractors(new ExcelExtractor(workbook));
                if (embeddedExtractors.length > 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : XLS Embedded");
                }
            } catch (IOException ioEx) {
                // if IOException, the document contain non office embedded object.
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unabled to download file. Please contact helpdesk.");
            }
        }
    }

    private static void hasXLSXEmbedded(MultipartFile attachmentFile) throws OpenXML4JException, IOException {
        try (InputStream inputStream = attachmentFile.getInputStream(); XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            List<PackagePart> embeddedObjects = workbook.getAllEmbeddedParts();
            if (!embeddedObjects.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : XLSX Embedded");
            }
        }
    }

    private static void hasXLSXEmbedded(File attachmentFile) throws OpenXML4JException, IOException {
        try (InputStream inputStream = new FileInputStream(attachmentFile); XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            List<PackagePart> embeddedObjects = workbook.getAllEmbeddedParts();
            if (!embeddedObjects.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid file : XLSX Embedded");
            }
        }
    }
}
