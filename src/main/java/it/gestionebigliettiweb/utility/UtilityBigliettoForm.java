package it.gestionebigliettiweb.utility;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.gestionebigliettiweb.model.Biglietto;

public class UtilityBigliettoForm {
	public static Biglietto createBigliettoFromParams(String provenienzaInputParam, String destinazioneInputParam,
			String dataStringParam, String prezzoInputStringParam) {

		Biglietto result = new Biglietto(provenienzaInputParam, destinazioneInputParam);

		if (NumberUtils.isCreatable(prezzoInputStringParam)) {
			result.setPrezzo(Integer.parseInt(prezzoInputStringParam));
		}
		result.setData(parseDataFromString(dataStringParam));

		return result;
	}

	public static boolean validateBigliettoBean(Biglietto articoloToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(articoloToBeValidated.getProvenienza())
				|| StringUtils.isBlank(articoloToBeValidated.getDestinazione())
				|| articoloToBeValidated.getData() == null || articoloToBeValidated.getPrezzo() == null
				|| articoloToBeValidated.getPrezzo() < 1) {
			return false;
		}
		return true;
	}

	public static LocalDate parseDataFromString(String dataStringParam) {
		if (StringUtils.isBlank(dataStringParam))
			return null;

		try {
			return LocalDate.parse(dataStringParam);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static Biglietto createBigliettoFromAll(Long idInputParam, String provenienzaInputParam,
			String destinazioneInputParam, String dataStringParam, String prezzoInputStringParam) {

		Biglietto result = new Biglietto(idInputParam, provenienzaInputParam, destinazioneInputParam);

		if (NumberUtils.isCreatable(prezzoInputStringParam)) {
			result.setPrezzo(Integer.parseInt(prezzoInputStringParam));
		}
		result.setData(parseDataFromString(dataStringParam));

		return result;
	}

}
