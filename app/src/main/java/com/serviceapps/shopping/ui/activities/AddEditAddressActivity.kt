package com.serviceapps.shopping.ui.activities

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.serviceapps.shopping.R
import com.serviceapps.shopping.firestore.FirestoreClass
import com.serviceapps.shopping.models.Address
import com.serviceapps.shopping.utils.Constants
import kotlinx.android.synthetic.main.activity_add_edit_address.*

/**
 * Add edit address screen.
 */
class AddEditAddressActivity : BaseActivity() {

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_add_edit_address)

        setupActionBar()

        // TODO Step 8: Assign the checked change listener on click of radio buttons for the address type.
        //START
        rg_type.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rb_other) {
                til_other_details.visibility = View.VISIBLE
            } else {
                til_other_details.visibility = View.GONE
            }
        }
        // END

        // TODO Step 7: Assign the on click event of submit button and save the address.
        btn_submit_address.setOnClickListener {
            saveAddressToFirestore()
        }
    }

    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_add_edit_address_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_add_edit_address_activity.setNavigationOnClickListener { onBackPressed() }
    }


    /**
     * A function to validate the address input entries.
     */
    private fun validateData(): Boolean {
        return when {

            TextUtils.isEmpty(et_full_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_please_enter_full_name),
                    true
                )
                false
            }

            TextUtils.isEmpty(et_phone_number.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_please_enter_phone_number),
                    true
                )
                false
            }

            TextUtils.isEmpty(et_address.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_please_enter_address), true)
                false
            }

            TextUtils.isEmpty(et_zip_code.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_please_enter_zip_code), true)
                false
            }

            rb_other.isChecked && TextUtils.isEmpty(
                et_zip_code.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_please_enter_zip_code), true)
                false
            }
            else -> {
                true
            }
        }
    }

    /**
     * A function to save the address to the cloud firestore.
     */
    private fun saveAddressToFirestore() {

        // Here we get the text from editText and trim the space
        val fullName: String = et_full_name.text.toString().trim { it <= ' ' }
        val phoneNumber: String = et_phone_number.text.toString().trim { it <= ' ' }
        val address: String = et_address.text.toString().trim { it <= ' ' }
        val zipCode: String = et_zip_code.text.toString().trim { it <= ' ' }
        val additionalNote: String = et_additional_note.text.toString().trim { it <= ' ' }
        val otherDetails: String = et_other_details.text.toString().trim { it <= ' ' }

        if (validateData()) {

            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))

            val addressType: String = when {
                rb_home.isChecked -> {
                    Constants.HOME
                }
                rb_office.isChecked -> {
                    Constants.OFFICE
                }
                else -> {
                    Constants.OTHER
                }
            }

            val addressModel = Address(
                FirestoreClass().getCurrentUserID(),
                fullName,
                phoneNumber,
                address,
                zipCode,
                additionalNote,
                addressType,
                otherDetails
            )


            // TODO Step 6: Call the function to save the address.
            // START
            FirestoreClass().addAddress(this@AddEditAddressActivity, addressModel)
            // END
        }
    }
    // END

     // TODO Step 3: Create a function to notify the success result of address saved.
    // START
    /**
     * A function to notify the success result of address saved.
     */
    fun addUpdateAddressSuccess() {

        // Hide progress dialog
        hideProgressDialog()

        Toast.makeText(
            this@AddEditAddressActivity,
            resources.getString(R.string.err_your_address_added_successfully),
            Toast.LENGTH_SHORT
        ).show()

        finish()
    }
// END
}